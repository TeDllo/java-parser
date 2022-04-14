package expression.generic;

import expression.exceptions.DivisionByZeroException;

public class FloatType implements NumberType<Float> {
    @Override
    public Float add(Float left, Float right) {
        return left + right;
    }

    @Override
    public Float subtract(Float left, Float right) {
        return left - right;
    }

    @Override
    public Float multiply(Float left, Float right) {
        return left * right;
    }

    @Override
    public Float divide(Float left, Float right) {
        return left / right;
    }

    @Override
    public Float min(Float left, Float right) {
        return Float.min(left, right);
    }

    @Override
    public Float max(Float left, Float right) {
        return Float.max(left, right);
    }

    @Override
    public Float count(Float value) {
        return (float) Integer.bitCount(Float.floatToIntBits(value));
    }

    @Override
    public Float negate(Float value) {
        return -value;
    }

    @Override
    public Float valueOf(int value) {
        return (float) value;
    }
}
