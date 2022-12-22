package concepts;

import adventofcode.aoc2022.day12.PathNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.EnumSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Sample tests to get familiar with the concepts of parametrized tests in Junit 5.
 * Official Documentation <a href="https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests">...</a>
 */
public class Junit5ParametrizedTests {

    // First test.`
    public class Numbers {

        public static boolean isOdd(int number) {
            return number % 2 != 0;
        }
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOddShouldReturnTrueForOddNumbers(int number) {
        assertTrue(Numbers.isOdd(number));
    }


    /*
     * Simple Values, nulls, and empty collections.
     * */
    public class Strings {

        public static boolean isBlank(final String input) {
            return input == null || input.trim().isEmpty();
        }
    }
    @ParameterizedTest
    @NullSource // Used to pass nulls
    @ValueSource(strings = {"", " "})
    public void isBlank_shouldReturnTrueForNullOrBlankStrings(final String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @NullAndEmptySource // Used to pass nulls and empty. Note that it works for Empty collections and arrays.
    @ValueSource(strings = {"", " ", "\t ", "\r\n\t "})
    public void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(final String input) {
        assertTrue(Strings.isBlank(input));
    }

    /**
     * ENUMS, and how to run test for all the possible values
     */

    @ParameterizedTest
    @EnumSource(value = Month.class) // 12 months in the Enum
    public void getValueForMonth_isAlwaysBetweenOneAndTwelve(Month month){
        final int monthNumber =month.getValue();
        assertTrue(monthNumber >=1 && monthNumber <=12);
    }

    @ParameterizedTest
    @EnumSource(value = Month.class,  names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"}) //Just named elements
    public void someMonths_Are30DaysLong(Month month){
        final boolean isLeapYear = false;
        assertEquals(30,month.length(isLeapYear));
    }



    /*
    * IN this case named elements will be excluded.
    * */
    @ParameterizedTest
    @EnumSource(
            value = Month.class,
            names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
            mode = EnumSource.Mode.EXCLUDE)
    void exceptFourMonths_OthersAre31DaysLong(Month month) {
        final boolean isALeapYear = false;
        assertEquals(31, month.length(isALeapYear));
    }


    /*
    * We can also Use Regular expressions...
    * */

    @ParameterizedTest
    @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
    void fourMonths_AreEndingWithBer(final Month month) {
        final EnumSet<Month> months =
                EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
        assertTrue(months.contains(month));
    }
    /*
    * CSV Literals. These are useful when Actual And expected are required.
    * */

    @ParameterizedTest
    @CsvSource ({"Test,TEST","tEst,TEST"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }
    @ParameterizedTest
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    void toLowerCase_ShouldGenerateTheExpectedLowercaseValue(String input, String expected) {
        String actualValue = input.toLowerCase();
        assertEquals(expected, actualValue);
    }


    /*
    * Methods : Up to here, Simple parameters where passed. it is impossible to pass complex objects without
    * dirty hacks.
    *
    * For complex arguments, we can pass methods as argument sources
    * */
    @ParameterizedTest
    @MethodSource("provideStringIsBlank") // If method is on different class, just use fully qualified name.

    void isBlank_ShouldReturnTrueforBlankAndNullStrings(final String input, final boolean expected){
        assertEquals(expected,Strings.isBlank(input));
    }

    public static Stream<Arguments> provideStringIsBlank() {
        return Stream.of(
                Arguments.of(null,true),
                Arguments.of("",true),
                Arguments.of(" ",true),
                Arguments.of("not blank",false)
        );
    }

    @ParameterizedTest
    @MethodSource // hmm, no method name ...
    void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
        assertTrue(Strings.isBlank(input));
    }

    private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
        return Stream.of(null, "", "  ");
    }


}
