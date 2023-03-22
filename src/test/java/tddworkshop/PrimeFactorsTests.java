package tddworkshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PrimeFactorsTests {

    private PrimeFactors primeFactors;

    @BeforeEach
    void setUp() {
        primeFactors = new PrimeFactors();
    }

    @Test
    public void given1_returnEmpty(){
        assertPrimeMembers(List.of(), 1);
    }

    @Test
    public void given2_return2(){
        assertPrimeMembers(List.of(2), 2);
    }

    @Test
    public void given3_return3(){
        assertPrimeMembers(List.of(3), 3);
    }

    @Test
    public void given4_return2_2(){
        assertPrimeMembers(List.of(2,2), 4);
    }

    @Test
    public void given6_return2_3(){
        assertPrimeMembers(List.of(2,3), 6);
    }

    @Test
    public void given8_return2_2_2(){
        assertPrimeMembers(List.of(2,2,2), 8);
    }


    @Test
    public void given9_return3_3(){
        assertPrimeMembers(List.of(3,3), 9);
    }


    private void assertPrimeMembers(List<Integer> of, int number) {
        List<Integer> factors = primeFactors.getPrimeFactors(number);
        Assertions.assertEquals(of,factors);
    }

}
