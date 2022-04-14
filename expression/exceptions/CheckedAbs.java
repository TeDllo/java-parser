package expression.exceptions;

import expression.base.Element;
import expression.base.UnaryOperation;

public class CheckedAbs extends UnaryOperation {

    public CheckedAbs(Element expression) {
        this(false, expression);
    }

    public CheckedAbs(boolean needBrackets, Element expression) {
        super(expression, needBrackets);
    }

    @Override
    public int operate(int value) {
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return value < 0 ? -value : value;
    }

    @Override
    public String getSign() {
        return "abs";
    }
}
