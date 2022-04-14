package expression.generic;

import java.util.Objects;

public class GenericConst<T> implements GenericElement<T> {

    private final T value;

    public GenericConst(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && expression.hashCode() == this.hashCode();
    }

    public T getValue() {
        return value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
