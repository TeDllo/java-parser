package expression;

import java.math.BigInteger;

import static expression.Priority.*;

public class Divide extends BinaryOperation {

    public Divide(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return left / right;
    }

    @Override
    public BigInteger operate(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    public int getPriority() {
        return DIVIDE_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority != MAXIMAL_PRIORITY || priority < DIVIDE_PRIORITY;
    }

    @Override
    public String getSign() {
        return "/";
    }
}
