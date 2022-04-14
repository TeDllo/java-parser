package expression.generic;

import expression.exceptions.MathException;
import expression.exceptions.ParseException;

public class GenericTabulator implements Tabulator {
    @Override
    public Object[][][] tabulate(String mode, String expression,
                                 int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        NumberType<?> type = null;
        switch (mode) {
            case "i":
                type = new IntegerType();
                break;
            case "d":
                type = new DoubleType();
                break;
            case "bi":
                type = new BigIntegerType();
                break;
            case "u":
                type = new UIntegerType();
                break;
            case "f":
                type = new FloatType();
                break;
            case "l":
                type = new LongType();
                break;
        }
        return calculate(type, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] calculate(NumberType<T> type, String expression,
                                       int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        GenericElement<T> element = new GenericExpressionParser<T>().parse(expression, type);

        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    try {
                        table[x - x1][y - y1][z - z1] =
                                element.evaluate(type.valueOf(x), type.valueOf(y), type.valueOf(z));
                    } catch (MathException e) {
//                        System.err.println(Arrays.toString(e.getStackTrace()));
                        table[x - x1][y - y1][z - z1] = null;
                    }
                }
            }
        }

        return table;

    }


}
