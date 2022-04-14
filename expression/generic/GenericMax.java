package expression.generic;

import static expression.base.Priority.MAX_PRIORITY;
import static expression.base.Priority.MIN_PRIORITY;

public class GenericMax<T> extends GenericBinaryOperation<T> {

    public GenericMax(GenericElement<T> left, GenericElement<T> right, NumberType<T> type) {
        super(left, right, type);
    }

    @Override
    public T operate(T left, T right) {
        return type.max(left, right);
    }

    @Override
    public int getPriority() {
        return MAX_PRIORITY;
    }

    @Override
    public String getSign() {
        return "max";
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority == MIN_PRIORITY;
    }
}
