package expression;

import java.math.BigInteger;

import static expression.Priority.*;

public class Add extends BinaryOperation {


    public Add(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return left + right;
    }

    @Override
    public BigInteger operate(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public int getPriority() {
        return ADD_PRIORITY;
    }

    @Override
    public String getSign() {
        return "+";
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return priority < ADD_PRIORITY;
    }
}
