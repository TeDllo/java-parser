package expression.generic;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerType implements NumberType<Integer> {

    @Override
    public Integer add(Integer left, Integer right) {
        if ((left < 0) == (right < 0) && (left < 0) != ((right + left) < 0)) {
            throw new OverflowException();
        }
        return left + right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        if ((left < 0) != (right < 0) && (left < 0) != ((left - right) < 0)) {
            throw new OverflowException();
        }

        return left - right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        if (right > 0 ? left > Integer.MAX_VALUE / right
                || left < Integer.MIN_VALUE / right
                : (right < -1 ? left > Integer.MIN_VALUE / right
                || left < Integer.MAX_VALUE / right
                : right == -1
                && left == Integer.MIN_VALUE)) {
            throw new OverflowException();
        }
        return left * right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException();
        } else if (right == 0) {
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
        if (value == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -value;
    }

    @Override
    public Integer valueOf(int value) {
        return value;
    }
}
