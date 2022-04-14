package expression.exceptions;

public class DivisionByZeroException extends MathException {
    public DivisionByZeroException() {
        super("Division by zero");
    }
}
