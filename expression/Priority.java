package expression;

public class Priority {
    public static final int ZERO_PRIORITY = 0;
    public static final int MIN_PRIORITY = 10;
    public static final int MAX_PRIORITY = 10;
    public static final int ADD_PRIORITY = 20;
    public static final int SUBTRACT_PRIORITY = 20;
    public static final int DIVIDE_PRIORITY = 30;
    public static final int MULTIPLY_PRIORITY = 31;
    public static final int NEGATE_PRIORITY = 40;
    public static final int LEADING_ZEROES_PRIORITY = 40;
    public static final int TRAILING_ZEROES_PRIORITY = 40;

    public static final int MAXIMAL_PRIORITY = 100;
}
