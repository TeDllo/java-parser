package expression.generic;

public interface GenericUnary<T> {
    T operate(T value);

    String getSign();
}
