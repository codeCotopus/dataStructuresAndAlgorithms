package adventofcode.aoc2022.Day8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day8Test {
    @Test
    public void part1Input() {
        assertEquals(1543, part1(Day8Input.INPUT));
    }

    @Test
    public void part1Sample() {
        assertEquals(21, part1(Day8Input.SAMPLE));
    }

    @Test
    public void part2Input() {
        assertEquals(595080, part2(Day8Input.INPUT));
    }

    @Test
    public void part2Sample() {
        assertEquals(8, part2(Day8Input.SAMPLE));
    }


    private static int part2(String input) {
        String[] rows = input.split("\n");
        int numberOfRows = rows.length;
        int numberOfColumns = rows[0].length();

        int[][] heightMap = new int[numberOfRows][numberOfColumns];


        mapTreeHeights(rows, numberOfRows, numberOfColumns, heightMap);

        int maxVisibility = 0;

        for (int i = 0; i < numberOfRows; i++) {

            for (int j = 0; j < numberOfColumns; j++) {

                int totalTreesVisible = 1;
                int visibleTrees =0;

                for (int x = 1; i + x < numberOfRows; x++) {
                    visibleTrees++;
                    if (heightMap[i][j] <= heightMap[i + x][j]) {
                        break;
                    }
                }
                totalTreesVisible*=visibleTrees;

                visibleTrees =0;
                for (int x = 1; j + x < numberOfColumns; x++) {
                    visibleTrees++;
                    if (heightMap[i][j] <= heightMap[i][j + x]) {
                        break;
                    }
                }
                totalTreesVisible*=visibleTrees;

                visibleTrees =0;
                for (int x = 1; i - x >= 0; x++) {
                    visibleTrees++;
                    if (heightMap[i][j] <= heightMap[i - x][j]) {
                        break;
                    }
                }
                totalTreesVisible*=visibleTrees;

                visibleTrees =0;
                for (int x = 1; j - x >= 0; x++) {
                    visibleTrees++;
                    if (heightMap[i][j] <= heightMap[i][j - x]) {
                        break;
                    }
                }
                totalTreesVisible*=visibleTrees;
                if (totalTreesVisible>maxVisibility) maxVisibility= totalTreesVisible;

            }

        }

        return maxVisibility;
    }

    private static void mapTreeHeights(String[] rows, int numberOfRows, int numberOfColumns, int[][] heightMap) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                heightMap[i][j] = Character.getNumericValue(rows[i].charAt(j));
            }
        }
    }

    private static int part1(String input) {
        String[] rows = input.split("\n");
        int numberOfRows = rows.length;
        int numberOfColumns = rows[0].length();

        int[][] heightMap = new int[numberOfRows][numberOfColumns];
        int[][] visibilityMap = new int[numberOfRows][numberOfColumns];
        int totalVisible = 0;

        mapTreeHeights(rows, numberOfRows, numberOfColumns, heightMap);

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (i == 0 || j == 0 || i == numberOfRows - 1 || j == numberOfColumns - 1) {
                    totalVisible++;
                    visibilityMap[i][j] = 1;
                    continue;
                }

                if (treeNotVisible(visibilityMap[i][j])) {
                    for (int x = 1; i + x < numberOfRows; x++) {
                        if (x + i + 1 == numberOfRows && heightMap[i][j] > heightMap[i + x][j]) {
                            totalVisible += 1;
                            visibilityMap[i][j] = 1;
                            break;
                        }
                        if (heightMap[i][j] <= heightMap[i + x][j]) break;
                    }
                }

                if (treeNotVisible(visibilityMap[i][j])) {
                    for (int x = 1; j + x < numberOfColumns; x++) {
                        if (x + j + 1 == numberOfColumns && heightMap[i][j] > heightMap[i][j + x]) {
                            totalVisible += 1;
                            visibilityMap[i][j] = 1;
                            break;
                        }
                        if (heightMap[i][j] <= heightMap[i][j + x]) break;
                    }
                }

                if (treeNotVisible(visibilityMap[i][j])) {
                    for (int x = 1; i - x >= 0; x++) {
                        if (i - x == 0 && heightMap[i][j] > heightMap[i - x][j]) {
                            totalVisible += 1;
                            visibilityMap[i][j] = 1;
                            break;
                        }
                        if (heightMap[i][j] <= heightMap[i - x][j]) break;
                    }
                }

                if (treeNotVisible(visibilityMap[i][j])) {
                    for (int x = 1; j - x >= 0; x++) {
                        if (j - x == 0 && heightMap[i][j] > heightMap[i][j - x]) {
                            totalVisible += 1;
                            visibilityMap[i][j] = 1;
                            break;
                        }
                        if (heightMap[i][j] <= heightMap[i][j - x]) break;
                    }
                }

            }
        }

        return totalVisible;
    }

    private static boolean treeNotVisible(int j) {
        return j != 1;
    }


}
