package expression.exceptions;

public interface CharSource {
    char next();

    boolean hasNext();

    void setPos(int pos);

    int getPos();
}
