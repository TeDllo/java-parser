package expression.exceptions;

import expression.base.BinaryOperation;
import expression.base.Element;

import static expression.base.Priority.SUBTRACT_PRIORITY;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if ((left < 0) != (right < 0) && (left < 0) != ((left - right) < 0)) {
            throw new OverflowException();
        }

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

