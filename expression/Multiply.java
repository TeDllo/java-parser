package expression;

import java.math.BigInteger;

import static expression.Priority.*;

public class Multiply extends BinaryOperation {
    public Multiply(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return left * right;
    }

    @Override
    public BigInteger operate(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public int getPriority() {
        return MULTIPLY_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return priority < MULTIPLY_PRIORITY - (1 - pos);
    }

    @Override
    public String getSign() {
        return "*";
    }
}
