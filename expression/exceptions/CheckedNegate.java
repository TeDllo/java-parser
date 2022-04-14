package expression.exceptions;

import expression.base.Element;
import expression.base.UnaryOperation;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(Element expression) {
        this(false, expression);
    }

    public CheckedNegate(boolean needBrackets, Element expression) {
        super(expression, needBrackets);
    }

    @Override
    public int operate(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -value;
    }

    @Override
    public String getSign() {
        return "-";
    }
}

