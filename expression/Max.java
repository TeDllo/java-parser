package expression;

import java.math.BigInteger;

public class Max extends BinaryOperation {

    public Max(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return Math.max(left, right);
    }

    @Override
    public BigInteger operate(BigInteger left, BigInteger right) {
        return left.max(right);
    }

    @Override
    public int getPriority() {
        return Priority.MAX_PRIORITY;
    }

    @Override
    public String getSign() {
        return "max";
    }
}
