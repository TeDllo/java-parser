package expression.base;

import static expression.base.Priority.MAX_PRIORITY;

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
    public String getSign() {
        return "min";
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority == MAX_PRIORITY;
    }
}
