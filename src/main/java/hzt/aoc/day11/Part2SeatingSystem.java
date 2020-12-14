package hzt.aoc.day11;

import java.util.ArrayList;
import java.util.List;

public class Part2SeatingSystem extends Day11Challenge {

    public Part2SeatingSystem() {
        super("Seating System part 2",
                "Now, instead of considering just the eight immediately adjacent seats, " +
                        "consider the first seat in each of those eight directions. " +

                        "For example, the empty seat below would see eight occupied seats. echter " +
                        "Given the new visibility method and the rule change for occupied seats becoming empty, " +
                        "once equilibrium is reached, how many seats end up occupied?");
    }

    @Override
    protected int checkOccupiedAndUpdateList(List<String> inputList) {
        List<String> newList = new ArrayList<>();
//        System.out.println("before: ");
//        inputList.forEach(System.out::println);
        int occupied = 0;
        char[][] grid = new char[inputList.size()][inputList.get(0).length()];
        char[][] newGrid = new char[inputList.size()][inputList.get(0).length()];
        for (int row = 0; row < inputList.size(); row++) {
            grid[row] = inputList.get(row).toCharArray();
            newGrid[row] = inputList.get(row).toCharArray();
            for (int col = 0; col < grid[row].length; col++) {
                char curChar = grid[row][col];
                int occupiedInLineOfSight = occupiedSeatsInLineOfSight(grid, row, col);
//                System.out.print(curChar + "->" + occupiedInLineOfSight + " ");
                if (applyRules(curChar, occupiedInLineOfSight, row, col, newGrid)) occupied++;
            }
//            System.out.println();
            newList.add(String.copyValueOf(newGrid[row]));
        }
//        System.out.println();
        inputList.clear();
        inputList.addAll(newList);
//        System.out.println("after");
//        inputList.forEach(System.out::println);
//        System.out.println();
        return occupied;
    }

    private static final int THRESHOLD_BECOMES_EMPTY = 5;

    private static final int[][] DIRECTIONS = {
            {1, 0}, {1, 1},
            {0, 1}, {-1, 1},
            {-1, 0}, {-1, -1},
            {0, -1}, {1, -1}};
    //TODO what is going on with the char array and empty chars

    int occupiedSeatsInLineOfSight(char[][] curGrid, int row, int col) {
        int occupiedInLineOfSight = 0;
        for (int[] dir : DIRECTIONS) {
            int dRow = row;
            int dCol = col;
            while (dRow >= 0 && dRow < curGrid.length
                    && dCol >= 0 && dCol < curGrid[0].length) {
                if (row != dRow || col != dCol) {
                    char checked = curGrid[dRow][dCol];
                    if (checked != FLOOR) {
                        if (checked == OCCUPIED_SEAT) occupiedInLineOfSight++;
                        break;
                    }
                }
                dCol += dir[0];
                dRow += dir[1];
            }
        }
        return occupiedInLineOfSight;
    }
    private boolean applyRules(char curChar, int occupiedInLineOfSight, int row, int col, char[][] newGrid) {
        if (curChar == EMPTY_SEAT && occupiedInLineOfSight == 0) newGrid[row][col] = OCCUPIED_SEAT;
        else if (curChar == OCCUPIED_SEAT && occupiedInLineOfSight >= THRESHOLD_BECOMES_EMPTY) {
            newGrid[row][col] = EMPTY_SEAT;
        }
        return newGrid[row][col] == OCCUPIED_SEAT;
    }


    @Override
    String getMessage(long value) {
        return String.format("The number of seats occupied after equilibrium: %d (not right yet...)%n", value);
    }
}
