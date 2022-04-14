package expression.generic;

public class GenericVariable<T> implements GenericElement<T> {

    private final String variable;

    public GenericVariable(String variable) {
        this.variable = variable;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (variable) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new RuntimeException("Wrong variable");
        }
    }

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && expression.toString().equals(this.toString());
    }

    @Override
    public String toString() {
        return variable;
    }

    @Override
    public int hashCode() {
        return variable.hashCode();
    }
}
