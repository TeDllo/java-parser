package expression.exceptions;

import expression.base.BinaryOperation;
import expression.base.Element;

import static expression.base.Priority.MULTIPLY_PRIORITY;

public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(Element left, Element right) {
        super(left, right);
    }

    public static void checkProductOverflow(int left, int right) {
        if (right > 0 ? left > Integer.MAX_VALUE / right
                || left < Integer.MIN_VALUE / right
                : (right < -1 ? left > Integer.MIN_VALUE / right
                || left < Integer.MAX_VALUE / right
                : right == -1
                && left == Integer.MIN_VALUE)) {
            throw new OverflowException();
        }
    }

    @Override
    public int operate(int left, int right) {
        checkProductOverflow(left, right);
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

