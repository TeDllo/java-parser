package expression.generic;

import static expression.base.Priority.ADD_PRIORITY;

public class GenericAdd<T> extends GenericBinaryOperation<T> {

    public GenericAdd(GenericElement<T> left, GenericElement<T> right, NumberType<T> type) {
        super(left, right, type);
    }

    @Override
    public T operate(T left, T right) {
        return type.add(left, right);
    }

    @Override
    public int getPriority() {
        return ADD_PRIORITY;
    }

    @Override
    public String getSign() {
        return "+";
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return priority < ADD_PRIORITY;
    }
}
