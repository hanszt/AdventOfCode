package hzt.aoc.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Part2DockingDataSeatingSystemTest {

    @Test
    void isOccupiedAfterUpdate() {
        Part2SeatingSystem part2SeatingSystem = new Part2SeatingSystem();
        int row = 3;
        int col = 3;
        char[][] grid = {
                {'L', 'L', 'L', 'L'},
                {'L', 'L', 'L', 'L'},
                {'L', 'L', '.', 'L'},
                {'L', '.', 'L', 'L'}};
        int occupied = part2SeatingSystem.occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(0, occupied);

    }

    @Test
    void isOccupiedAfterUpdate1() {
        Part2SeatingSystem part2SeatingSystem = new Part2SeatingSystem();
        int row = 3;
        int col = 2;
        char[][] grid = {
                {'#', '#', '#', '#'},
                {'#', '#', '#', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'}};
        int occupied = part2SeatingSystem.occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(5, occupied);

    }

    @Test
    void isOccupiedAfterUpdate3() {
        Part2SeatingSystem part2SeatingSystem = new Part2SeatingSystem();
        int row = 3;
        int col = 2;
        char[][] grid = {
                {'#', '#', '#', '#'},
                {'#', '#', '#', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'}};
        int occupied = part2SeatingSystem.occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(5, occupied);

    }

    @Test
    void isOccupiedAfterUpdate2() {
        Part2SeatingSystem part2SeatingSystem = new Part2SeatingSystem();
        int row = 2;
        int col = 2;
        char[][] grid = {
                {'#', '#', '#', '#'},
                {'#', '#', '#', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'}};
        int occupied = part2SeatingSystem.occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(7, occupied);

    }

    @Test
    void isOccupiedAfterUpdate4() {
        List<Integer> occupiedValues = new ArrayList<>();
        Part2SeatingSystem part2SeatingSystem = new Part2SeatingSystem();
        char[][] grid = {
                {'#', '.', '#', '#', '#'},
                {'.', '.', '#', '.', '#'},
                {'#', '#', '#', '#', '#'},
                {'#', '.', '#', '#', '#'},
                {'#', '.', '#', '#', '#'},
                {'#', '.', '#', '#', '#'}};
        Integer[] expected = {
                3, 4, 5, 5, 3,
                4, 6, 6, 8, 5,
                5, 5, 8, 8, 5,
                5, 7, 8, 8, 5,
                4, 7, 7, 8, 5,
                3, 5, 5, 5, 3,};

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int occupied = part2SeatingSystem.occupiedSeatsInLineOfSight(grid, row, col);
                occupiedValues.add(occupied);
                System.out.print(occupied + ",");
            }
            System.out.println();
        }

        for (char[] array : grid) {
            for (char c : array) System.out.print(c + " ");
            System.out.println();
        }
        Integer[] actual = occupiedValues.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    void isOccupiedAfterUpdate5() {
        List<Integer> occupiedValues = new ArrayList<>();
        Part2SeatingSystem part2SeatingSystem = new Part2SeatingSystem();
        char[][] grid = {
                {'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '.', '#', '#'},
                {'#', '.', '#', '.', '#', '.', '.', '#', '.', '.'},
                {'#', '#', '#', '#', '.', '#', '#', '.', '#', '#'},
                {'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'},
                {'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'},
                {'.', '.', '#', '.', '#', '.', '.', '.', '.', '.'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                {'#', '.', '#', '#', '#', '#', '#', '#', '.', '#'},
                {'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'}};
        Integer[] expected = {
                3, 5, 5, 5, 5, 5, 5, 5, 5, 3,
                4, 7, 7, 7, 7, 7, 7, 7, 6, 5,
                5, 8, 8, 8, 8, 8, 8, 6, 7, 5,
                5, 8, 8, 8, 8, 8, 8, 8, 7, 5,
                5, 8, 7, 8, 8, 8, 8, 8, 8, 5,
                5, 7, 8, 8, 8, 8, 8, 8, 7, 5,
                5, 7, 7, 8, 7, 7, 7, 7, 7, 5,
                5, 6, 8, 8, 8, 8, 8, 8, 7, 4,
                4, 7, 7, 8, 8, 8, 7, 7, 7, 5,
                3, 5, 5, 5, 5, 5, 5, 5, 5, 3,};

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int occupied = part2SeatingSystem.occupiedSeatsInLineOfSight(grid, row, col);
                occupiedValues.add(occupied);
                System.out.print(occupied + ",");
            }
            System.out.println();
        }

        for (char[] array : grid) {
            for (char c : array) System.out.print(c + " ");
            System.out.println();
        }
        Integer[] actual = occupiedValues.toArray(new Integer[0]);
        Assertions.assertArrayEquals(expected, actual);

    }

}
