package expression.generic;

public class GenericCount<T> extends GenericUnaryOperation<T> {
    public GenericCount(boolean needBrackets, GenericElement<T> expression, NumberType<T> type) {
        super(expression, needBrackets, type);
    }

    @Override
    public T operate(T value) {
        return type.count(value);
    }

    @Override
    public String getSign() {
        return "count";
    }
}
