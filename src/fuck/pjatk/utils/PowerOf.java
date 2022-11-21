package fuck.pjatk.utils;

import java.math.BigDecimal;

public class PowerOf {
    /**
     * Metoda dla zadania o szukaniu największego k do potegi n takiego, ze x >= k^n.
     *
     * @param x liczba otrzymana z serwera
     * @param power potęga podana w zadaniu
     * @return szukana liczba k
     */
    public static BigDecimal findPower(BigDecimal x, int power) {
        var k = new BigDecimal(1);

        while (k.pow(power).compareTo(x) < 0) {
            k = k.add(BigDecimal.ONE);
        }

        return k.subtract(BigDecimal.ONE);
    }
}
