package expression;

import java.math.BigInteger;

public class Min extends BinaryOperation {

    public Min(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int getPriority() {
        return Priority.MIN_PRIORITY;
    }

    @Override
    public int operate(int left, int right) {
        return Math.min(left, right);
    }

    @Override
    public BigInteger operate(BigInteger left, BigInteger right) {
        return left.min(right);
    }

    @Override
    public String getSign() {
        return "min";
    }
}
