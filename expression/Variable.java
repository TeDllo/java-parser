package expression;

import java.math.BigInteger;

public class Variable implements Element {

    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variable) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new RuntimeException("Wrong variable");
        }
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && expression.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }
}
