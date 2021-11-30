package hzt.aoc.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static hzt.aoc.day11.Day11Challenge.FLOOR;
import static hzt.aoc.day11.Day11Challenge.OCCUPIED_SEAT;

class Part2CrabCupsDockingDataSeatingSystemTest {

    @Test
    void isOccupiedAfterUpdate() {
        int row = 3;
        int col = 3;
        char[][] grid = {
                {'L', 'L', 'L', 'L'},
                {'L', 'L', 'L', 'L'},
                {'L', 'L', '.', 'L'},
                {'L', '.', 'L', 'L'}};
        int occupied = occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(0, occupied);

    }

    @Test
    void isOccupiedAfterUpdate1() {
        int row = 3;
        int col = 2;
        char[][] grid = {
                {'#', '#', '#', '#'},
                {'#', '#', '#', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'}};
        int occupied = occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(5, occupied);

    }

    @Test
    void isOccupiedAfterUpdate3() {
        int row = 3;
        int col = 2;
        char[][] grid = {
                {'#', '#', '#', '#'},
                {'#', '#', '#', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'}};
        int occupied = occupiedSeatsInLineOfSight(grid, row, col);
        System.out.println();
        for (char[] array : grid) {
            for (char c : array) System.out.print(c);
            System.out.println();
        }
        Assertions.assertEquals(5, occupied);

    }

    @Test
    void isOccupiedAfterUpdate2() {
        int row = 2;
        int col = 2;
        char[][] grid = {
                {'#', '#', '#', '#'},
                {'#', '#', '#', '#'},
                {'#', '#', '.', '#'},
                {'#', '.', '#', '#'}};
        int occupied = occupiedSeatsInLineOfSight(grid, row, col);
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
                int occupied = occupiedSeatsInLineOfSight(grid, row, col);
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
                int occupied = occupiedSeatsInLineOfSight(grid, row, col);
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

    private static final int[][] DIRECTIONS = {
            {1, 0}, {1, 1},
            {0, 1}, {-1, 1},
            {-1, 0}, {-1, -1},
            {0, -1}, {1, -1}};

    int occupiedSeatsInLineOfSight(char[][] curGrid, int row, int col) {
        int occupiedInLineOfSight = 0;
        for (int[] dir : DIRECTIONS) {
            if (occupiedInLineOfSight(curGrid, row, col, dir)) occupiedInLineOfSight++;
        }
        return occupiedInLineOfSight;
    }

    private boolean occupiedInLineOfSight(char[][] curGrid, int row, int col, int[] dir) {
        int dRow = row;
        int dCol = col;
        while (dRow >= 0 && dRow < curGrid.length
                && dCol >= 0 && dCol < curGrid[0].length) {
            if (row != dRow || col != dCol) {
                char checked = curGrid[dRow][dCol];
                if (checked != FLOOR) {
                    if (checked == OCCUPIED_SEAT) return true;
                    break;
                }
            }
            dCol += dir[0];
            dRow += dir[1];
        }
        return false;
    }


}
