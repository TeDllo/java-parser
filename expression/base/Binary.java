package expression.base;

import java.math.BigInteger;

public interface Binary {

    int operate(int left, int right);

    String getSign();

    Element getLeft();

    Element getRight();
}
