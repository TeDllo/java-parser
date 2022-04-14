package expression.generic;

import expression.exceptions.ParseException;

public interface GenericTripleParser<T> {
    GenericTripleExpression<T> parse(String expression, NumberType<T> type) throws ParseException;
}
