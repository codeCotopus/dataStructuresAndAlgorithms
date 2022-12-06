package adventofcode.aoc2022.day6;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static adventofcode.aoc2022.day6.Day6Input.INPUT;
import static adventofcode.aoc2022.day6.Day6Input.SAMPLE;

@Slf4j
public class Day6Test {

    @Test
    public void part1sample(){
      log.info("Marker found {}",  findMarker(SAMPLE));
    }

    @Test
    public void part1input(){
        log.info("Marker found {}",  findMarker(INPUT));
    }

    private static int findMarker(String input) {
        for (int i = 3; i < input.length(); i++) {
            Set seen = new HashSet();
            boolean markerSeen = false;

            for (int j = i-3; j <=i ; j++) {
               if (seen.contains(input.charAt(j))) break;
                seen.add(input.charAt(j));
               if (seen.size() == 4) markerSeen = true;
            }

            if (markerSeen)
                return i+1;
        }
        return -1;
    }
}
