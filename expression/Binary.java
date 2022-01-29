package expression;

import java.math.BigInteger;

public interface Binary {

    int operate(int left, int right);

    BigInteger operate(BigInteger left, BigInteger right);

    String getSign();

    Element getLeft();

    Element getRight();
}
