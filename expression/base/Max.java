package expression.base;

import static expression.base.Priority.MAX_PRIORITY;
import static expression.base.Priority.MIN_PRIORITY;

public class Max extends BinaryOperation {

    public Max(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        return Math.max(left, right);
    }

    @Override
    public int getPriority() {
        return MAX_PRIORITY;
    }

    @Override
    public String getSign() {
        return "max";
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority == MIN_PRIORITY;
    }
}
