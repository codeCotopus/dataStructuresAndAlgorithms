package adventofcode.aoc2022.day9;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day9Test {

    @Test
    public void part1Sample() {
        assertEquals(13, solvePart2(Day9Input.SAMPLE, 1));
    }

    @Test
    public void part1Input() {
        assertEquals(6376, solvePart2(Day9Input.INPUT, 1));
    }

    @Test
    public void part2Sample1() {
        assertEquals(1, solvePart2(Day9Input.SAMPLE, 9));
    }

    @Test
    public void part2Sample2() {
        assertEquals(36, solvePart2(Day9Input.SAMPLE2, 9));
    }

    @Test
    public void part2Input() {
        assertEquals(2607, solvePart2(Day9Input.INPUT, 9));
    }


    private int solvePart2(String input, int numberOfKnots) {
        List<Pair<String, Integer>> motions = getMotions(input);

        Pair<Integer, Integer> headCurrentPosition = Pair.of(0, 0);

        List<Knot> knots = IntStream.range(0, numberOfKnots).mapToObj(i -> new Knot(0, 0)).collect(Collectors.toList());

        Set<Pair<Integer, Integer>> positionsVisitedByTail = new HashSet<>();


        for (Pair<String, Integer> motion : motions) {
            for (int i = 0; i < motion.getRight(); i++) {
                switch (motion.getLeft()) {
                    case "U" ->
                            headCurrentPosition = Pair.of(headCurrentPosition.getLeft() + 1, headCurrentPosition.getRight());
                    case "R" ->
                            headCurrentPosition = Pair.of(headCurrentPosition.getLeft(), headCurrentPosition.getRight() + 1);
                    case "D" ->
                            headCurrentPosition = Pair.of(headCurrentPosition.getLeft() - 1, headCurrentPosition.getRight());
                    case "L" ->
                            headCurrentPosition = Pair.of(headCurrentPosition.getLeft(), headCurrentPosition.getRight() - 1);
                }

                knots.get(0).followHead(headCurrentPosition);
                IntStream.range(1, numberOfKnots).forEach(k -> knots.get(k).followHead(knots.get(k - 1).position));
                positionsVisitedByTail.add(knots.get(numberOfKnots - 1).position);
            }
        }
        return positionsVisitedByTail.size();
    }

    @Test
    public void headAndTailAdjacent_TailDoesntMove() {
        assertTailMovement(0, 0, 0, 0);
        assertTailMovement(1, 0, 0, 0);
        assertTailMovement(0, 1, 0, 0);
        assertTailMovement(1, 1, 0, 0);
    }

    @Test
    public void headAndTailPerpendicularNotTouching_TaiFollowsMove() {
        assertTailMovement(2, 0, 1, 0);
        assertTailMovement(-2, 0, -1, 0);
        assertTailMovement(0, 2, 0, 1);
        assertTailMovement(0, -2, 0, -1);

    }

    @Test
    public void headAndTailDiagonalNotTouching_TailcatchUp() {
        assertTailMovement(2, 1, 1, 1);
        assertTailMovement(-2, 1, -1, 1);
        assertTailMovement(1, 2, 1, 1);
        assertTailMovement(-1, -2, -1, -1);
    }


    private void assertTailMovement(int headX, int headY, int tailX, int tailY) {
        Knot tail = new Knot(0, 0);
        tail.followHead(Pair.of(headX, headY));
        assertEquals(tailX, tail.position.getLeft());
        assertEquals(tailY, tail.position.getRight());
    }

    @Test
    public void canTellWhenPointsAreTouching() {
        assertTrue(pointsAreTouching(Pair.of(0, 0), Pair.of(0, 0)));
        assertTrue(pointsAreTouching(Pair.of(1, 0), Pair.of(0, 0)));
        assertTrue(pointsAreTouching(Pair.of(-1, 0), Pair.of(0, 0)));
        assertTrue(pointsAreTouching(Pair.of(0, 1), Pair.of(0, 0)));
        assertTrue(pointsAreTouching(Pair.of(0, -1), Pair.of(0, 0)));
        assertTrue(pointsAreTouching(Pair.of(1, -1), Pair.of(0, 0)));
        assertFalse(pointsAreTouching(Pair.of(0, -2), Pair.of(0, 0)));
        assertFalse(pointsAreTouching(Pair.of(0, -2), Pair.of(0, 0)));
    }

    private boolean pointsAreTouching(Pair<Integer, Integer> head, Pair<Integer, Integer> tail) {
        return getChebyshebDistance(head, tail) < 2;
    }

    @Test
    public void canConputeChebyshebDistance() {
        assertEquals(0, getChebyshebDistance(Pair.of(0, 0), Pair.of(0, 0)));
        assertEquals(1, getChebyshebDistance(Pair.of(1, 0), Pair.of(0, 0)));
        assertEquals(1, getChebyshebDistance(Pair.of(1, 1), Pair.of(0, 0)));
        assertEquals(2, getChebyshebDistance(Pair.of(2, 0), Pair.of(0, 0)));
        assertEquals(2, getChebyshebDistance(Pair.of(2, 1), Pair.of(0, 0)));
        assertEquals(3, getChebyshebDistance(Pair.of(3, 0), Pair.of(0, 0)));
        assertEquals(1, getChebyshebDistance(Pair.of(3, 0), Pair.of(2, 1)));
        assertEquals(3, getChebyshebDistance(Pair.of(3, 1), Pair.of(0, 0)));
    }

    private int getChebyshebDistance(Pair<Integer, Integer> x, Pair<Integer, Integer> y) {
        return Math.max(abs(y.getRight() - x.getRight()), abs(y.getLeft() - x.getLeft()));
    }


    private static List<Pair<String, Integer>> getMotions(String sample) {

        String[] split = sample.split("\n");
        String regex = "(\\w) (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        List<Pair<String, Integer>> motions = Arrays.stream(split).map(x -> {
            Matcher matcher = pattern.matcher(x);
            matcher.find();
            String direction = matcher.group(1);
            Integer times = Integer.valueOf(matcher.group(2));
            Pair<String, Integer> coordinates = Pair.of(direction, times);
            return coordinates;
        }).collect(Collectors.toList());
        return motions;
    }

    private class Knot {
        Pair<Integer, Integer> position;

        private Knot(Integer x, Integer y) {
            this.position = Pair.of(x, y);
        }

        public void followHead(Pair<Integer, Integer> headPosition) {
            if (pointsAreTouching(position, headPosition)) return;
            Integer headX = headPosition.getLeft();
            Integer headY = headPosition.getRight();

            int xMovement = headX - position.getLeft(); // 0,1,2;
            int yMovement = headY - position.getRight(); // 0,1,2;

            if (abs(xMovement) > abs(yMovement)) {
                this.position = Pair.of(xMovement > 0 ? headX - 1 : headX + 1, headY);
            }
            if (abs(xMovement) < abs(yMovement)) {
                this.position = Pair.of(headX, yMovement > 0 ? headY - 1 : headY + 1);
            }
            if (abs(xMovement) == abs(yMovement)) {
                this.position = Pair.of(xMovement > 0 ? headX - 1 : headX + 1, yMovement > 0 ? headY - 1 : headY + 1);
            }


        }

    }
}


