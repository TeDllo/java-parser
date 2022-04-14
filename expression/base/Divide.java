package expression.base;

import java.math.BigInteger;

import static expression.base.Priority.*;

public class Divide extends BinaryOperation {

    public Divide(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return left / right;
    }

    @Override
    public int getPriority() {
        return DIVIDE_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority != OPERAND_PRIORITY || priority < DIVIDE_PRIORITY;
    }

    @Override
    public String getSign() {
        return "/";
    }
}
