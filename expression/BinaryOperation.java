package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class BinaryOperation implements Binary, Element {

    protected final Element leftExpression;
    protected final Element rightExpression;

    public BinaryOperation(Element leftExpression, Element rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
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

    private void insertBrackets(StringBuilder output, Element expression, int pos) {
        if (needBrackets(pos, expression.getPriority())) {
            output.append("(");
            expression.fillToMiniString(output);
            output.append(")");
        } else {
            expression.fillToMiniString(output);
        }
    }

    @Override
    public int evaluate(int x) {
        return operate(leftExpression.evaluate(x), rightExpression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operate(leftExpression.evaluate(x, y, z), rightExpression.evaluate(x, y, z));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return operate(leftExpression.evaluate(x), rightExpression.evaluate(x));
    }

    @Override
    public Element getLeft() {
        return leftExpression;
    }

    @Override
    public Element getRight() {
        return rightExpression;
    }
    
/*
    Prev version:
    @Override
    public boolean equals(Object expression) {
        return expression instanceof Binary && this.hashCode() == expression.hashCode();
    }
*/

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && ((Binary) expression).getLeft().equals(leftExpression)
                && ((Binary) expression).getRight().equals(rightExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftExpression, rightExpression, getClass());
    }
}
