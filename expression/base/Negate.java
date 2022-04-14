package expression.base;

public class Negate extends UnaryOperation {

    public Negate(boolean needBrackets, Element expression) {
        super(expression, needBrackets);
    }

    @Override
    public int operate(int value) {
        return -value;
    }

    @Override
    public String getSign() {
        return "-";
    }
}
