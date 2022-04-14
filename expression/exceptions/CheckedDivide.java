package expression.exceptions;

import expression.base.BinaryOperation;
import expression.base.Element;

import static expression.base.Priority.*;

public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException();
        } else if (right == 0) {
            throw new DivisionByZeroException();
        }
        return left / right;
    }

    @Override
    public int getPriority() {
        return DIVIDE_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority <= MULTIPLY_PRIORITY || priority < DIVIDE_PRIORITY;
    }

    @Override
    public String getSign() {
        return "/";
    }
}

