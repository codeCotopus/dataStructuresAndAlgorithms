package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

//https://www.hackerrank.com/challenges/java-primality-test/problem?isFullScreen=true
public class PrimatlityTestTests {
    @Test
    public void primalityTests() {
        isPrime("13");
        isPrime("2367495770217142995264827948666809233066409497699870112003149352380375124855230068487109373226251983");
        isPrime("900900900900990990990991");
        isNotPrime("1");
        isNotPrime("4");
        isNotPrime("2367495770217142995264827948666809233066409497699870112003149352380375124855230068487109373226251985");

    }

    private void isNotPrime(final String s) {
        Assertions.assertFalse(PrimalityTestableNumber.of(s).isPrime());
    }

    private static void isPrime(final String s) {
        Assertions.assertTrue(PrimalityTestableNumber.of(s).isPrime());
    }
}
