package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class UIntegerType implements NumberType<Integer> {

    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (right == 0) {
            throw new DivisionByZeroException();
        }
        return left / right;
    }

    @Override
    public Integer min(Integer left, Integer right) {
        return Integer.min(left, right);
    }

    @Override
    public Integer max(Integer left, Integer right) {
        return Integer.max(left, right);
    }

    @Override
    public Integer count(Integer value) {
        return Integer.bitCount(value);
    }

    @Override
    public Integer negate(Integer value) {
        return -value;
    }

    @Override
    public Integer valueOf(int value) {
        return value;
    }
}
