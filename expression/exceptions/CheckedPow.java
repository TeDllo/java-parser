package expression.exceptions;

import expression.base.BinaryOperation;
import expression.base.Element;

import static expression.base.Priority.POW_PRIORITY;
import static expression.exceptions.CheckedMultiply.checkProductOverflow;

public class CheckedPow extends BinaryOperation {
    public CheckedPow(Element left, Element right) {
        super(left, right);
    }

    private int binPow(int base, int pow) {
        if (base == 0) {
            return 0;
        }
        if (pow == 0) {
            return 1;
        }
        if (pow % 2 == 1) {
            int result = binPow(base, pow - 1);
            checkProductOverflow(result, base);
            return result * base;
        } else {
            int result = binPow(base, pow / 2);
            checkProductOverflow(result, result);
            return result * result;
        }
    }

    @Override
    public int operate(int left, int right) {
        if (left == 0 && right == 0) {
            throw new BaseException("Can't evaluate 0 ** 0");
        }
        if (right < 0) {
            throw new BaseException("Can't evaluate negative pow: " + right);
        }
        return binPow(left, right);
    }

    @Override
    public int getPriority() {
        return POW_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return priority < POW_PRIORITY + pos;
    }

    @Override
    public String getSign() {
        return "**";
    }
}


