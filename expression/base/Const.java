package expression.base;

public class Const implements Element {

    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object expression) {
        return expression != null
                && expression.getClass() == this.getClass()
                && ((Const) expression).getValue() == value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
