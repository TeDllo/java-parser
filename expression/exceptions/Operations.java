package expression.exceptions;

public enum Operations {
    MIN("min", 2),
    MAX("max", 2),

    ADD("+", 4),
    SUBTRACT("-", 4),

    MULTIPLY("*", 6),
    DIVIDE("/", 6),

    POW("**", 8),
    LOG("//",  8);

    public final String sign;
    public final int childLevel;

    Operations(String sign, int childLevel) {
        this.sign = sign;
        this.childLevel = childLevel;
    }
}
