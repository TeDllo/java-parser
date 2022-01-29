package expression;

import java.math.BigInteger;

public interface Unary {
    int operate(int value);

    BigInteger operate(BigInteger value);

    String getSign();
}
