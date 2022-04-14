package expression.generic;

import java.util.Objects;

public abstract class GenericBinaryOperation<T> implements GenericBinary<T>, GenericElement<T> {

    protected final GenericElement<T> leftExpression;
    protected final GenericElement<T> rightExpression;
    protected final NumberType<T> type;

    public GenericBinaryOperation(GenericElement<T> leftExpression,
                                  GenericElement<T> rightExpression,
                                  NumberType<T> type) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        fillToString(output);
        return output.toString();
    }

    @Override
    public void fillToString(StringBuilder output) {
        output.append("(");
        leftExpression.fillToString(output);
        output.append(" ").append(getSign()).append(" ");
        rightExpression.fillToString(output);
        output.append(")");
    }

    @Override
    public String toMiniString() {
        StringBuilder output = new StringBuilder();
        fillToMiniString(output);
        return output.toString();
    }

    @Override
    public void fillToMiniString(StringBuilder output) {
        insertBrackets(output, leftExpression, 0);
        output.append(" ").append(getSign()).append(" ");
        insertBrackets(output, rightExpression, 1);
    }

    private void insertBrackets(StringBuilder output, GenericElement<T> expression, int pos) {
        if (needBrackets(pos, expression.getPriority())) {
            output.append("(");
            expression.fillToMiniString(output);
            output.append(")");
        } else {
            expression.fillToMiniString(output);
        }
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return operate(leftExpression.evaluate(x, y, z), rightExpression.evaluate(x, y, z));
    }

    @Override
    public GenericElement<T> getLeft() {
        return leftExpression;
    }

    @Override
    public GenericElement<T> getRight() {
        return rightExpression;
    }

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && expression.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression, getClass());
    }
}
