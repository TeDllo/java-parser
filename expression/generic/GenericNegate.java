package expression.generic;

public class GenericNegate<T> extends GenericUnaryOperation<T> {

    public GenericNegate(boolean needBrackets, GenericElement<T> expression, NumberType<T> type) {
        super(expression, needBrackets, type);
    }

    @Override
    public T operate(T value) {
        return type.negate(value);
    }

    @Override
    public String getSign() {
        return "-";
    }
}
