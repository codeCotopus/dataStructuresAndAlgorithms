package problems;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;


public class BigDecimalArray {
    @Getter
    final BigDecimal[] elements;

    public BigDecimalArray(final String[] stringEntries) {
        elements = Arrays.stream(stringEntries)
                .map(s -> new BigDecimal(s))
                .toArray(BigDecimal[]::new);
    }
    private BigDecimalArray(final BigDecimal[] array) {
        this.elements = array;
    }

    public static BigDecimalArray of(final String[] stringEntries) {
        return new BigDecimalArray(stringEntries);
    }

    public BigDecimalArray sorted() {
        BigDecimal[] clone = elements.clone();
        Arrays.sort(elements.clone(), 0, elements.length, Comparator.reverseOrder());

        return new BigDecimalArray(clone);
    }


}
