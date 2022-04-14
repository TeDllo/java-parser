package expression.generic;

import static expression.base.Priority.SUBTRACT_PRIORITY;

public class GenericSubtract<T> extends GenericBinaryOperation<T> {
    public GenericSubtract(GenericElement<T> left, GenericElement<T> right, NumberType<T> type) {
        super(left, right, type);
    }

    @Override
    public T operate(T left, T right) {
        return type.subtract(left, right);
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
