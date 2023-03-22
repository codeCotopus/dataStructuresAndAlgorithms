package adventofcode.aoc2022.day13;

import lombok.Getter;

import java.util.Arrays;

@Getter

public class Packet {
    private final int[] data;
    private final String rawString;

    private Packet(final String input) {
        rawString = input;
        String trimmed = input.substring(1, input.length() - 1);

        this.data = Arrays.stream(trimmed.split(","))
                .filter(n -> !n.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static Packet of(String input) {
        return new Packet(input);
    }
}
