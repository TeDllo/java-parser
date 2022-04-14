package expression.generic;

import static expression.base.Priority.MAX_PRIORITY;

public class GenericMin<T> extends GenericBinaryOperation<T> {

    public GenericMin(GenericElement<T> left, GenericElement<T> right, NumberType<T> type) {
        super(left, right, type);
    }

    @Override
    public int getPriority() {
        return Priority.MIN_PRIORITY;
    }

    @Override
    public T operate(T left, T right) {
        return type.min(left, right);
    }

    @Override
    public String getSign() {
        return "min";
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority == MAX_PRIORITY;
    }
}
