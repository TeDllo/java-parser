package expression;

import java.math.BigInteger;

public class Const implements Element {

    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    public Const(BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && ((Const) expression).getValue().equals(this.getValue());
    }

    public Number getValue() {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return value.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return (BigInteger) value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
