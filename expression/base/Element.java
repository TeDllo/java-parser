package expression.base;

public interface Element extends Expression, TripleExpression {
    default int getPriority() {
        return Priority.OPERAND_PRIORITY;
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
