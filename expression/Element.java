package expression;

public interface Element extends Expression, TripleExpression, BigIntegerExpression {
    default int getPriority() {
        return Priority.MAXIMAL_PRIORITY;
    }

    default boolean needBrackets(int pos, int priority) {
        return false;
    }

    default void fillToString(StringBuilder output) {
        output.append(this);
    }

    default void fillToMiniString(StringBuilder output) {
        output.append(toMiniString());
    }
}
