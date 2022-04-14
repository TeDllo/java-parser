package expression.generic;

public abstract class GenericUnaryOperation<T> implements GenericElement<T>, GenericUnary<T> {

    private final GenericElement<T> expression;
    private final boolean needBrackets;
    protected final NumberType<T> type;

    public GenericUnaryOperation(GenericElement<T> expression, boolean needBrackets, NumberType<T> type) {
        this.expression = expression;
        this.needBrackets = needBrackets;
        this.type = type;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(expression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        if (needBrackets) {
            return getSign() + "(" + expression + ")";
        }
        return getSign() + expression;
    }

    @Override
    public String toMiniString() {
        if (expression instanceof GenericBinary) {
            return getSign() + "(" + expression.toMiniString() + ")";
        }
        return getSign() + " " + expression.toMiniString();
    }
}
