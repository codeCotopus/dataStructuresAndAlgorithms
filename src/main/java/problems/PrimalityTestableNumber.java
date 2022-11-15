package problems;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class PrimalityTestableNumber {
    final BigInteger n;

    public static PrimalityTestableNumber of(String s) {
        return new PrimalityTestableNumber(new BigInteger(s));
    }

    public boolean isPrime() {
        return n.isProbablePrime(2);
    }
}
