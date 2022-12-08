package adventofcode.aoc2022.day6;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static adventofcode.aoc2022.day6.Day6Input.INPUT;
import static adventofcode.aoc2022.day6.Day6Input.SAMPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day6Test {

    @Test
    public void part1sample(){
      assertEquals(7,findMarker(SAMPLE, 3));
    }

    @Test
    public void part1input(){
        assertEquals(1542, findMarker(INPUT, 3));
    }

    @Test
    public void part2sample(){
       assertEquals(19, findMarker(SAMPLE,13));
    }

    @Test
    public void part2input(){
        assertEquals(3153,  findMarker(INPUT,13));
    }

    private static int findMarker(String input, int patternSize) {
        for (int i = patternSize; i < input.length(); i++) {
            Set seen = new HashSet();
            boolean markerSeen = false;

            for (int j = i- patternSize; j <=i ; j++) {
               if (seen.contains(input.charAt(j))) break;

                seen.add(input.charAt(j));
               if (seen.size() == patternSize +1) markerSeen = true;
            }

            if (markerSeen)
                return i+1;
        }
        return -1;
    }
}
