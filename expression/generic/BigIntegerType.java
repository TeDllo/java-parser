package expression.generic;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerType implements NumberType<BigInteger> {

    @Override
    public BigInteger valueOf(int value) {
        return BigInteger.valueOf(value);
    }

    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        if (right.compareTo(BigInteger.valueOf(0)) == 0) {
            throw new DivisionByZeroException();
        }
        return left.divide(right);
    }

    @Override
    public BigInteger min(BigInteger left, BigInteger right) {
        return left.min(right);
    }

    @Override
    public BigInteger max(BigInteger left, BigInteger right) {
        return left.max(right);
    }

    @Override
    public BigInteger count(BigInteger value) {
        return BigInteger.valueOf(value.bitCount());
    }

    @Override
    public BigInteger negate(BigInteger value) {
        return value.negate();
    }
}
