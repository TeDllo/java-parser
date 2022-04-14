package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class LongType implements NumberType<Long> {
    @Override
    public Long add(Long left, Long right) {
        return left + right;
    }

    @Override
    public Long subtract(Long left, Long right) {
        return left - right;
    }

    @Override
    public Long multiply(Long left, Long right) {
        return left * right;
    }

    @Override
    public Long divide(Long left, Long right) {
        if (right == 0) {
            throw new DivisionByZeroException();
        }
        return left / right;
    }

    @Override
    public Long min(Long left, Long right) {
        return Long.min(left, right);
    }

    @Override
    public Long max(Long left, Long right) {
        return Long.max(left, right);
    }

    @Override
    public Long count(Long value) {
        return (long) Long.bitCount(value);
    }

    @Override
    public Long negate(Long value) {
        return -value;
    }

    @Override
    public Long valueOf(int value) {
        return (long) value;
    }
}
