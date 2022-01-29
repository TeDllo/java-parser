package expression;

import java.math.BigInteger;

public class LeadingZeroes extends UnaryOperation {

    public LeadingZeroes(boolean needBrackets, Element expression) {
        super(expression, needBrackets);
    }

    @Override
    public int operate(int value) {
        return Integer.numberOfLeadingZeros(value);
    }

    @Override
    public BigInteger operate(BigInteger value) {
        throw new RuntimeException("Forbidden method");
    }

    @Override
    public String getSign() {
        return "l0";
    }
}
