package expression.generic;

import expression.base.Priority;

public interface GenericElement<T> extends GenericTripleExpression<T> {
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
