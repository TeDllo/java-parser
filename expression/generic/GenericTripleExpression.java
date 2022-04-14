package expression.generic;

import expression.base.ToMiniString;

public interface GenericTripleExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z);
}
