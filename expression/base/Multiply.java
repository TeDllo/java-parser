package expression.base;

import static expression.base.Priority.*;

public class Multiply extends BinaryOperation {
    public Multiply(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return left * right;
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
