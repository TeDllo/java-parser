package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {

    @Override
    public TripleExpression parse(String expression) {
        return new TripleExpressionParser(new StringCharSource(expression)).parse();
    }

    private static class TripleExpressionParser extends BaseParser {
        public TripleExpressionParser(CharSource source) {
            super(source);
        }

        public TripleExpression parse() {
            return parseExpression();
        }

        private Element parseExpression() {
            Element expression = parseMinMax();
            if (!end()) {
                expect(')');
            }
            return expression;
        }

        private Element parseMinMax() {
            skipWhiteSpaces();
            Element left = parseAddSubtract();
            while (test("min") || test("max")) {
                if (take("min")) {
                    left = new Min(left, parseAddSubtract());
                } else {
                    expect("max");
                    left = new Max(left, parseAddSubtract());
                }
            }
            return left;
        }

        private Element parseAddSubtract() {
            skipWhiteSpaces();
            Element left = parseMultiplyDivide();
            while (test('+') || test('-')) {
                skipWhiteSpaces();
                if (take('+')) {
                    left = new Add(left, parseMultiplyDivide());
                } else if (take('-')) {
                    left = new Subtract(left, parseMultiplyDivide());
                }
            }
            return left;
        }

        private Element parseMultiplyDivide() {
            Element left = parseOperand();
            skipWhiteSpaces();
            while (test('*') || test('/')) {
                if (take('*')) {
                    left = new Multiply(left, parseOperand());
                } else if (take('/')) {
                    left = new Divide(left, parseOperand());
                }
                skipWhiteSpaces();
            }
            return left;
        }

        private Element parseOperand() {
            skipWhiteSpaces();
            if (take('(')) {
                return parseExpression();
            } else if (take("l0")) {
                return parseLeadingZeroes();
            } else if (take("t0")) {
                return parseTrailingZeroes();
            } else if (take('-')) {
                return parseNegate();
            } else if (isVariable()) {
                return parseVariable();
            } else if (isDigit()) {
                return new Const(parseInt());
            }
            throw source.error("Invalid operand");
        }

        private int parseInt() {
            return parseInt(false);
        }

        private int parseInt(boolean isNegative) {
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
                throw source.error("Invalid number: " + number);
            }
        }

        private Element parseNegate() {
            if (isDigit()) {
                return new Const(parseInt(true));
            }
            skipWhiteSpaces();
            return new Negate(test('('), parseOperand());
        }

        private Element parseLeadingZeroes() {
            skipWhiteSpaces();
            return new LeadingZeroes(test('('), parseOperand());
        }

        private Element parseTrailingZeroes() {
            skipWhiteSpaces();
            return new TrailingZeroes(test('('), parseOperand());
        }

        private Element parseVariable() {
            return new Variable(Character.toString(take()));
        }

        private boolean isVariable() {
            return between('x', 'z');
        }

        private boolean isDigit() {
            return between('0', '9');
        }

    }
}
