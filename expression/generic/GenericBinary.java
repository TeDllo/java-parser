package expression.generic;

public interface GenericBinary<T> {

    T operate(T left, T right);

    String getSign();

    GenericElement<T> getLeft();

    GenericElement<T> getRight();
}
