package expression.exceptions;

import expression.base.BinaryOperation;
import expression.base.Element;

import static expression.base.Priority.ADD_PRIORITY;

public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if ((left < 0) == (right < 0) && (left < 0) != ((right + left) < 0)) {
            throw new OverflowException();
        }
        return left + right;
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
