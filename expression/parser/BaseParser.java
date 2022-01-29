package expression.parser;

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

    protected void expect(char expected) {
        if (!take(expected)) {
            throw source.error(String.format(
                    "Expected '%s', found '%s'",
                    expected, ch
            ));
        }
    }

    protected void expect(String expected) {
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
