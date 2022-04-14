package expression.exceptions;

import expression.base.BinaryOperation;
import expression.base.Element;

import static expression.base.Priority.LOG_PRIORITY;

public class CheckedLog extends BinaryOperation {
    public CheckedLog(Element left, Element right) {
        super(left, right);
    }

    @Override
    public int operate(int left, int right) {
        if (right <= 0 || right == 1) {
            throw new BaseException("Wrong log base: " + right);
        }
        if (left <= 0) {
            throw new BaseException("Wrong log number: " + left);
        }
        int result = 0;
        while (left >= right) {
            left /= right;
            result++;
        }
        return result;
    }

    @Override
    public int getPriority() {
        return LOG_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return priority < LOG_PRIORITY + pos;
    }

    @Override
    public String getSign() {
        return "//";
    }
}


