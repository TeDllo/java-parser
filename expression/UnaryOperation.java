package expression;

import java.math.BigInteger;

public abstract class UnaryOperation implements Element, Unary {

    private final Element expression;
    private final boolean needBrackets;

    public UnaryOperation(Element expression, boolean needBrackets) {
        this.expression = expression;
        this.needBrackets = needBrackets;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(expression.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return operate(expression.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return operate(expression.evaluate(x));
    }

    @Override
    public String toString() {
        if (needBrackets) {
            return getSign() + "(" + expression + ")";
        }
        return getSign() + expression;
    }

    @Override
    public String toMiniString() {
        if (expression instanceof Binary) {
            return getSign() + "(" + expression.toMiniString() + ")";
        }
        return getSign() + " " + expression.toMiniString();
    }
}
