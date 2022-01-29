package expression;

import java.math.BigInteger;

public class TrailingZeroes extends UnaryOperation {

    public TrailingZeroes(boolean needBrackets, Element expression) {
        super(expression, needBrackets);
    }

    @Override
    public int operate(int value) {
        return Integer.numberOfTrailingZeros(value);
    }

    @Override
    public BigInteger operate(BigInteger value) {
        return value.compareTo(BigInteger.ZERO) == 0
                ? BigInteger.ZERO
                : BigInteger.valueOf(value.getLowestSetBit());
    }

    @Override
    public String getSign() {
        return "t0";
    }
}
