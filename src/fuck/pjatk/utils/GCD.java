package fuck.pjatk.utils;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Klasa do obliczania największego wspólnego dzielnika.
 */
public class GCD {
    public static BigDecimal gcd(BigDecimal num1, BigDecimal num2) {
        if (num2.compareTo(BigDecimal.ZERO) == 0) return num1;

        return gcd(num2, num1.remainder(num2));
    }

    public static BigDecimal gcd(BigDecimal... nums) {
        return Arrays.stream(nums).reduce(GCD::gcd).orElse(BigDecimal.ZERO);
    }
}