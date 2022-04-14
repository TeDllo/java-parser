package expression;

import expression.exceptions.ParseException;
import expression.generic.*;

public class TestingModule {

    /*
        Tabulator:
            mode — режим работы

            Режим	Тип
            i	    int с детекцией переполнений
            d	    double
            bi	    BigInteger
            u       int без детекции переполнения
            l       long без проверки переполнения
            f       float без проверки на переполнение

            expression — вычисляемое выражение;
            x1, x2; y1, y2; z1, z2 — диапазоны изменения переменных (включительно).
     */

    public static void main(String[] args) {
        Tabulator tabulator = new GenericTabulator();

        String expression = "(x + 2) * y - z";
        String mode = "i";

        try {
            Object[][][] result = tabulator.tabulate(mode, expression, 0, 0, 0, 0, 0, 0);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
