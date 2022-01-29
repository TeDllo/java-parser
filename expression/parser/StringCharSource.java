package expression.parser;

public class StringCharSource implements CharSource {

    private final String source;
    private int pos;

    public StringCharSource(String source) {
        this.source = source;
    }

    @Override
    public char next() {
        return source.charAt(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < source.length();
    }

    @Override
    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public IllegalArgumentException error(String message) {
        return new IllegalArgumentException(String.format(
                "%d : %s", pos, message
        ));
    }

    public int getPos() {
        return pos;
    }

}
