package expression.generic;

import static expression.base.Priority.DIVIDE_PRIORITY;
import static expression.base.Priority.OPERAND_PRIORITY;

public class GenericDivide<T> extends GenericBinaryOperation<T> {

    public GenericDivide(GenericElement<T> left, GenericElement<T> right, NumberType<T> type) {
        super(left, right, type);
    }

    @Override
    public T operate(T left, T right) {
        return type.divide(left, right);
    }

    @Override
    public int getPriority() {
        return DIVIDE_PRIORITY;
    }

    @Override
    public boolean needBrackets(int pos, int priority) {
        return pos == 1 && priority != OPERAND_PRIORITY || priority < DIVIDE_PRIORITY;
    }

    @Override
    public String getSign() {
        return "/";
    }
}
