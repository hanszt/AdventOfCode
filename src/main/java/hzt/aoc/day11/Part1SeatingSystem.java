package hzt.aoc.day11;

import java.util.ArrayList;
import java.util.List;

public class Part1SeatingSystem extends Day11Challenge {

    public Part1SeatingSystem() {
        super("Seating System part 1",
                "Simulate your seating area by applying the seating rules repeatedly until no seats change state. " +
                        "How many seats end up occupied?");
    }

    @Override
    protected int checkOccupiedAndUpdateList(List<String> inputList) {
        int occupied = 0;
        List<String> newList = new ArrayList<>();
        for (int row = 0; row < inputList.size(); row++) {
            String curRow = inputList.get(row);
            String upperRow = row > 0 ? inputList.get(row - 1) : null;
            String lowerRow = row < inputList.size() - 1 ? inputList.get(row + 1) : null;
            occupied += checkAndUpdateRow(upperRow, curRow, lowerRow, newList);
        }
        inputList.clear();
        inputList.addAll(newList);
        return occupied;
    }

    private static final int THRESHOLD_BECOMES_EMPTY = 4;


    private int checkAndUpdateRow(String upperRow, String curRow, String lowerRow, List<String> newList) {
        int occupied = 0;
        char[] charsNewRow = curRow.toCharArray();
        for (int col = 0; col < charsNewRow.length; col++) {
            String neighbours = extractNeighBours(upperRow, curRow, lowerRow, col);
            int occupiedNeighbours = 0;
            for (char c : neighbours.toCharArray()) if (c == OCCUPIED_SEAT) occupiedNeighbours++;
            if (charsNewRow[col] == OCCUPIED_SEAT && occupiedNeighbours >= THRESHOLD_BECOMES_EMPTY) {
                charsNewRow[col] = EMPTY_SEAT;
            }
            if (charsNewRow[col] == EMPTY_SEAT && !neighbours.contains(String.valueOf(OCCUPIED_SEAT))) {
                charsNewRow[col] = OCCUPIED_SEAT;
            }
            if (charsNewRow[col] == OCCUPIED_SEAT) occupied++;
        }
        newList.add(String.copyValueOf(charsNewRow));
        return occupied;
    }

    private String extractNeighBours(String upperRow, String curRow, String lowerRow, int col) {
        String upperNeighBours = extractNeighboursByRow(upperRow, col);
        String lowerNeighBours = extractNeighboursByRow(lowerRow, col);
        String leftNeighBour = curRow.substring(col > 0 ? col - 1 : col, col);
        String rightNeighBour = curRow.substring(col < curRow.length() - 1 ? col + 1 : col, col < curRow.length() - 1 ? col + 2 : col);
        return upperNeighBours.concat(lowerNeighBours).concat(leftNeighBour).concat(rightNeighBour);
    }

    private String extractNeighboursByRow(String row, int col) {
        String neighBours;
        if (row != null) {
            neighBours = row.substring(col > 0 ? col - 1 : col, col < row.length() - 1 ? col + 2 : col + 1);
        } else neighBours = "";
        return neighBours;
    }



}
