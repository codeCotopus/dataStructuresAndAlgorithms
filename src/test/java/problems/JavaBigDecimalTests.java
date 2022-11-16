package problems;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.stream.IntStream;

//https://www.hackerrank.com/challenges/java-bigdecimal/problem
public class JavaBigDecimalTests {

    @Test
    public void javaBigDecimalTest() {

        String[] entries = new String[]{
                "-100",
                "50",
                "0",
                "56.6",
                "90",
                "0.12",
                ".12",
                "02.34",
                "000.000"
        };

        final BigDecimal[] elements = BigDecimalArray.of(entries).sorted().getElements();

        Assertions.assertTrue(
                IntStream.range(1, elements.length)
                        .anyMatch(i -> elements[i]
                        .compareTo(elements[i - 1]) == -1)
        );
    }
}
