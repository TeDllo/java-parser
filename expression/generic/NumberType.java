package expression.generic;

public interface NumberType<T> {

    // binary

    T add(T left, T right);

    T subtract(T left, T right);

    T multiply(T left, T right);

    T divide(T left, T right);

    T min(T left, T right);

    T max(T left, T right);

    // unary

    T count(T value);

    T negate(T value);

    T valueOf(int value);
}
