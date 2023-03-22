package adventofcode.aoc2022.day13;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;


@Slf4j
public class Day13Test {
    public static Stream<Arguments> getSamplePairs() {
        return Arrays.stream(Day13Input.SAMPLE_PAIRS)
                .map(x -> Arguments.of(x));
    }

        @ParameterizedTest
    @MethodSource("getSamplePairs")
    public void canCreatePacketPair(final String input) {
        String[] strings = input.lines().toArray(String[]::new);

        Arrays.stream(strings[0].split(",")).forEach(n -> log.info("FIRST {}", n));
        Arrays.stream(strings[1].split(",")).forEach(n -> log.info("SECOND {}", n));
    }






    @Test
    public void firstPairIsOrdered(){
        String[] strings = {"[1]","[1]"};
        ordered(strings[0],strings[1]);
    }

    private void ordered(String left, String right) {



        String[] leftElements = left.split(",");
        String[] rightElements = right.split(",");
        int indexFirstString =0;
        int indexSecondString =0;



        // Starts with [ or starts with number



    }

    @Test
    public void emptyArrayIsEmptyArray() {
        PacketAnalyzer analyzer = new PacketAnalyzer();
        String s = "[]";
        int[] array = analyzer.stringToArray(s);
        Assertions.assertEquals(0, array.length);
    }

    @Test
    public void singleElementIsSingleElementArray() {
        PacketAnalyzer analyzer = new PacketAnalyzer();
        String s = "4";
        int[] array = analyzer.stringToArray(s);
        Assertions.assertEquals(1, array.length);
        Assertions.assertEquals(4, array[0]);
    }

    @Test
    public void singleElementArrayIsSingleElementArray() {
        PacketAnalyzer analyzer = new PacketAnalyzer();
        String s = "[4]";
        int[] array = analyzer.stringToArray(s);
        Assertions.assertEquals(1, array.length);
        Assertions.assertEquals(4, array[0]);
    }
    @Test
    public void doubleElementArrayIsSingleElementArray() {
        PacketAnalyzer analyzer = new PacketAnalyzer();
        String s = "[4,3]";
        int[] array = analyzer.stringToArray(s);
        Assertions.assertEquals(2, array.length);
        Assertions.assertEquals(4, array[0]);
        Assertions.assertEquals(3, array[1]);
    }


    @Test
    public void recursiveStripping(){
        String s = "[[[1]],[2]]";
        CompositeThingy thingy = new CompositeThingy(s);
        int element = thingy.element;
    }
    






    
    private class PacketAnalyzer {

        public int[] stringToArray(String s) {

            if (s.startsWith("[") && s.endsWith("]")){
                if (s.equals("[]")) {
                    return new int[0];
                } else {
                    String substring = s.substring(1, s.length() - 1); //Strip brackets
                    return stringToArray(substring);
                }
            }

            String[] split = s.split(",");
            if (split.length == 1){
                try {
                    int i = parseInt(s);
                    int[] x = {i};
                    return x;
                } catch (NumberFormatException e) {
                    return new int[0];
                }
            } else {
                int []  x= new int[split.length];
                for (int i = 0; i < split.length; i++) {
                    x[i] = parseInt(split[i]);
                }
                return x;
            }
        }
    }
}
