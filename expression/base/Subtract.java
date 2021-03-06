package expression.base;

import static expression.base.Priority.*;

public class Subtract extends BinaryOperation {
    public Subtract(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return left - right;
    }

    @Override
    public int getPriority() {
        return SUBTRACT_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return priority <= SUBTRACT_PRIORITY - (1 - pos);
    }

    @Override
    public String getSign() {
        return "-";
    }
}
