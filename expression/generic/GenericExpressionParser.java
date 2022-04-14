package expression.generic;

import expression.exceptions.*;

import java.util.Map;
import java.util.function.BiFunction;

public class GenericExpressionParser<T> implements GenericTripleParser<T> {

    @Override
    public GenericElement<T> parse(String expression, NumberType<T> type) throws ParseException {
        return new GenericParser(new StringCharSource(expression), type).parse();
    }

    private class GenericParser extends BaseParser {

        private NumberType<T> type;

        private static final int MIN_MAX_LEVEL = 0;
        private static final int OPERAND_LEVEL = 8;

        private final Operations[] OPERATIONS = Operations.values();

        private final Map<String,
                BiFunction<GenericElement<T>, GenericElement<T>, GenericElement<T>>
                > objects = Map.of(
                "+", (left, right) -> new GenericAdd<>(left, right, type),
                "-", (left, right) -> new GenericSubtract<>(left, right, type),
                "*", (left, right) -> new GenericMultiply<>(left, right, type),
                "/", (left, right) -> new GenericDivide<>(left, right, type),
                "min", (left, right) -> new GenericMin<>(left, right, type),
                "max", (left, right) -> new GenericMax<>(left, right, type)
        );

        private int bracketBalance = 0;

        public GenericParser(CharSource source, NumberType<T> type) {
            super(source);
            this.type = type;
        }

        public GenericElement<T> parse() throws ParseException {
            return parseExpression();
        }

        private GenericElement<T> parseExpression() throws ParseException {
            GenericElement<T> expression = parseOperation(MIN_MAX_LEVEL);
            if (!end()) {
                if (bracketBalance == 0) {
                    throw new BracketsException("Extra closing bracket. Position: " + source.getPos());
                }
                bracketBalance--;
                expect(')');
            } else if (bracketBalance != 0) {
                throw new BracketsException("Missing closing bracket. Position: " + source.getPos());
            }
            return expression;
        }

        private GenericElement<T> parseOperation(int level) throws ParseException {
            if (level == OPERAND_LEVEL) {
                return parseOperand();
            }
            GenericElement<T> element = parseOperation(OPERATIONS[level].childLevel);
            boolean isFinished = false;
            while (!isFinished) {
                isFinished = true;
                skipWhiteSpaces();
                for (int opLevel = level; opLevel <= level + 1; opLevel++) {
                    if (take(OPERATIONS[opLevel].sign)) {
                        if (level == MIN_MAX_LEVEL && Character.isLetterOrDigit(ch)) {
                            expectWhitespace(OPERATIONS[opLevel].name());
                        }
                        element = objects.get(OPERATIONS[opLevel].sign).apply(
                                element, parseOperation(OPERATIONS[level].childLevel)
                        );
                        isFinished = false;
                    }
                }
            }
            return element;
        }

        private GenericElement<T> parseOperand() throws ParseException {
            skipWhiteSpaces();
            if (take('(')) {
                bracketBalance++;
                return parseExpression();
            } else if (take('-')) {
                return parseNegate();
            } else if (take("count")) {
                return parseCount();
            } else if (isVariable()) {
                return parseVariable();
            } else if (isDigit()) {
                return new GenericConst<>(parseInt(false));
            }
            throw new ParseException("Expected operand, found nothing. Position: " + source.getPos());
        }

        private T parseInt(boolean isNegative) throws ParseException {
            if (take('0')) {
                return type.valueOf(0);
            }
            StringBuilder number = new StringBuilder(isNegative ? "-" : "");
            while (isDigit()) {
                number.append(take());
            }
            try {
                return type.valueOf(Integer.parseInt(number.toString()));
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid number: " + number + ". Position: " + source.getPos());
            }
        }

        private GenericElement<T> parseNegate() throws ParseException {
            if (isDigit()) {
                return new GenericConst<>(parseInt(true));
            }
            skipWhiteSpaces();
            return new GenericNegate<>(test('('), parseOperand(), type);
        }

        private GenericElement<T> parseCount() throws ParseException {
            skipWhiteSpaces();
            return new GenericCount<>(test('('), parseOperand(), type);
        }

        private GenericElement<T> parseVariable() {
            return new GenericVariable<>(takeVariable());
        }

        private boolean isDigit() {
            return Character.isDigit(ch);
        }

    }
}
