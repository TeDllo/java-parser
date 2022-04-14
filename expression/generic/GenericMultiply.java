package expression.generic;

import static expression.base.Priority.MULTIPLY_PRIORITY;

public class GenericMultiply<T> extends GenericBinaryOperation<T> {
    public GenericMultiply(GenericElement<T> left, GenericElement<T> right, NumberType<T> type) {
        super(left, right, type);
    }

    @Override
    public T operate(T left, T right) {
        return type.multiply(left, right);
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
