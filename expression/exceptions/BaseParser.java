package expression.exceptions;

public abstract class BaseParser {

    protected static final char END = 0;
    protected final CharSource source;
    protected char ch;

    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(char expected) {
        return ch == expected;
    }

    protected boolean test(String expected) {
        int beginPos = source.getPos();
        char beginChar = ch;
        for (char c : expected.toCharArray()) {
            if (!take(c)) {
                source.setPos(beginPos);
                ch = beginChar;
                return false;
            }
        }
        source.setPos(beginPos);
        ch = beginChar;
        return true;
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean take(String expected) {
        if (test(expected)) {
            for (char c : expected.toCharArray()) {
                take(c);
            }
            return true;
        }
        return false;
    }

    protected void expect(char expected) throws ParseException {
        if (!take(expected)) {
            throw new ParseException(String.format(
                    "Expected '%s', found '%s'. Position: %d",
                    expected, ch, source.getPos())
            );
        }
    }

    protected void expectWhitespace(String operation) throws ParseException {
        if (!Character.isWhitespace(ch)) {
            throw new SpaceException(
                    String.format("Expected space after %s. Position: %d",
                            operation, source.getPos())
            );
        }
        take();
    }

    protected void expect(String expected) throws ParseException {
        for (char c : expected.toCharArray()) {
            expect(c);
        }
    }

    protected boolean end() {
        return test(END);
    }

    protected boolean between(char min, char max) {
        return min <= ch && ch <= max;
    }

    protected boolean isVariable() {
        return between('x', 'z');
        /*
        return Character.isLetter(ch) || ch == '_';
         */
    }

    protected String takeVariable() {
        StringBuilder variable = new StringBuilder();
        while (isVariable()) {
            variable.append(take());
        }
        return variable.toString();
    }

    protected void skipWhiteSpaces() {
        while (takeWhiteSpace() || take('\n') || take('\r') || take('\t')) {
            /* Do nothing */
        }
    }

    protected boolean takeWhiteSpace() {
        if (Character.isWhitespace(ch)) {
            take();
            return true;
        }
        return false;
    }

}
