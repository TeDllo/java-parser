package expression.exceptions;

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

    public int getPos() {
        return pos;
    }

}
