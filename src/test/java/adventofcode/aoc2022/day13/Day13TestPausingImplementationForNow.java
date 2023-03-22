package adventofcode.aoc2022.day13;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day13TestPausingImplementationForNow {


    public static Stream<Arguments> getSamplePairs() {
        return Arrays.stream(Day13Input.SAMPLE_PAIRS)
                .map(x -> Arguments.of(x));
    }

   // @ParameterizedTest
    @MethodSource("getSamplePairs")
    public void canCreatePacketPair(final String input) {
        String[] strings = input.lines().toArray(String[]::new);
        PacketPair packetPair = PacketPair.of(input);
        assertEquals(strings[0],packetPair.getFirstPacket());
        assertEquals(strings[1],packetPair.getSecondPacket());
    }

    @ParameterizedTest
    @ValueSource (strings = {
            "[]\n[]",
            "[1]\n[1]",
            "[1,2]\n[1,2]"
    })
    public void sameListIsOrdered(final String input){
        assertTrue(PacketPair.of(input).isOrdered());
    }

    @ParameterizedTest
    @ValueSource (strings = {
            "[]\n[]",
            "[1]\n[2]",
            "[2,8]\n[3,9]",
            "[1,1]\n[1,1,4]",
            "[]\n[1,2]"
    })
    public void orderedListIsOrdered(final String input){
        assertTrue(PacketPair.of(input).isOrdered());
    }

    @ParameterizedTest
    @ValueSource (strings = {
            "[1,2,3]\n[1,2]",
            "[1]\n[]"
    })
    public void LongerLeftPackageIsNotOrdered(final String input){
        assertFalse(PacketPair.of(input).isOrdered());
    }


    @ParameterizedTest
    @ValueSource (strings = {
            "[2]\n[1]",
            "[1,4]\n[1,2]",

    })
    public void unOrderedPackageIsNotOrdered(final String input){
        assertFalse(PacketPair.of(input).isOrdered());
    }

    @ParameterizedTest
    @ValueSource (strings = {
            "[1,2]",
            "[2]",
    })
    public void canParseIntPacket (final String input){
        assertEquals(input.split(",").length,
                Packet.of(input).getData().length);
    }
    @Test
    public void canParseEmptyPacket(){
            assertEquals(0,
                    Packet.of("[]").getData().length);
    }

//    @ParameterizedTest
//    @ValueSource (strings = {
//            "[[]]",
//
//    })
//    public void canParseCompositePacakge (final String input){
//        assertEquals(input.split(",").length,
//                Packet.of(input).getData().length);
//    }
//    @Test
//    public void canParseCompositeEmptyPacket(){
//        assertEquals(0,
//                Packet.of("[[]]").getData().length);
//    }
//


    @Test
    public void testingRecursiveRegex(){
        String s = "[1,[2,[3,[4,[5,6,7]]]],8,9]";
        String regex = "\\[(.+?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        boolean b = matcher.find();
        log.info("here");


    }

}
