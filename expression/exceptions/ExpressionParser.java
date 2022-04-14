package expression.exceptions;

import expression.base.Const;
import expression.base.Element;
import expression.base.TripleExpression;
import expression.base.Variable;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) throws ParseException {
        return new TripleExpressionParser(new StringCharSource(expression)).parse();
    }

    private static class TripleExpressionParser extends BaseParser {

        private static final int MIN_MAX_LEVEL = 0;
        private static final int OPERAND_LEVEL = 8;
        private static final Operations[] OPERATIONS = Operations.values();

        private int bracketBalance = 0;

        public TripleExpressionParser(CharSource source) {
            super(source);
        }

        public TripleExpression parse() throws ParseException {
            return parseExpression();
        }

        private Element parseExpression() throws ParseException {
            Element expression = parseOperation(MIN_MAX_LEVEL);
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

        private Element parseOperation(int level) throws ParseException {
            if (level == OPERAND_LEVEL) {
                return parseOperand();
            }
            Element element = parseOperation(OPERATIONS[level].childLevel);
            boolean isFinished = false;
            while (!isFinished) {
                isFinished = true;
                skipWhiteSpaces();
                for (int opLevel = level; opLevel <= level + 1; opLevel++) {
                    if (take(OPERATIONS[opLevel].sign)) {
                        if (level == MIN_MAX_LEVEL && Character.isLetterOrDigit(ch)) {
                            expectWhitespace(OPERATIONS[opLevel].name());
                        }
//                        element = OPERATIONS[opLevel].create.apply(element, parseOperation(OPERATIONS[level].childLevel));
                        isFinished = false;
                    }
                }
            }
            return element;
        }

        private Element parseOperand() throws ParseException {
            skipWhiteSpaces();
            if (take('(')) {
                bracketBalance++;
                return parseExpression();
            } else if (take('-')) {
                return parseNegate();
            } else if (isVariable()) {
                return parseVariable();
            } else if (isDigit()) {
                return new Const(parseInt(false));
            } else if (take("abs")) {
                return parseAbs();
            }
            throw new ParseException("Expected operand, found nothing. Position: " + source.getPos());
        }

        private int parseInt(boolean isNegative) throws ParseException {
            if (take('0')) {
                return 0;
            }
            StringBuilder number = new StringBuilder(isNegative ? "-" : "");
            while (isDigit()) {
                number.append(take());
            }
            try {
                return Integer.parseInt(number.toString());
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid number: " + number + ". Position: " + source.getPos());
            }
        }

        private Element parseAbs() throws ParseException {
            if (!test('(')) {
                expectWhitespace("ABS");
            }
            skipWhiteSpaces();
            return new CheckedAbs(test('('), parseOperand());
        }

        private Element parseNegate() throws ParseException {
            if (isDigit()) {
                return new Const(parseInt(true));
            }
            skipWhiteSpaces();
            return new CheckedNegate(test('('), parseOperand());
        }

        private Element parseVariable() {
            return new Variable(takeVariable());
        }

        private boolean isDigit() {
            return Character.isDigit(ch);
        }

    }
}

