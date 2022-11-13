package problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HouseRobberTests {

    @Test
    public void noHouses_0() {
        assertHousesMaxIs(0);
    }


    @Test
    public void emptyHouses_0() {
        assertHousesMaxIs(0,0,0,0);
    }

    @Test
    public void oneHouse_HouseValue(){
        assertHousesMaxIs(1, 1);
    }

    @Test
    public void twoHouses_MaxValue(){
        assertHousesMaxIs(3,1,3);
        assertHousesMaxIs(3,3,1);
    }

    @Test
    public void multipleHouses(){
        assertHousesMaxIs(6,1,2,3,4);
        assertHousesMaxIs(19,6, 7, 1, 3, 8, 2, 4 );

    }

    private static void assertHousesMaxIs(int expected, int ... housesValues) {
        Assertions.assertEquals(
                expected,
                HouseRobber.compute (housesValues)
        );
    }
}
