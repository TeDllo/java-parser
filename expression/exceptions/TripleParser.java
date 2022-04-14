package expression.exceptions;

import expression.base.TripleExpression;

@FunctionalInterface
public interface TripleParser {
    TripleExpression parse(String expression) throws Exception;
}