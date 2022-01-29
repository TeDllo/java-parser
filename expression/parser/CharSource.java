package expression.parser;

public interface CharSource {
    char next();

    boolean hasNext();

    IllegalArgumentException error(String message);

    void setPos(int pos);

    int getPos();
}
