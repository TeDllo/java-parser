package expression;

import java.math.BigInteger;

public class Negate extends UnaryOperation {

    public Negate(boolean needBrackets, Element expression) {
        super(expression, needBrackets);
    }

    @Override
    public int operate(int value) {
        return -value;
    }

    @Override
    public BigInteger operate(BigInteger value) {
        return BigInteger.ZERO.subtract(value);
    }

    @Override
    public String getSign() {
        return "-";
    }
}
