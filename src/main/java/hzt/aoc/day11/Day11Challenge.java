package hzt.aoc.day11;

import hzt.aoc.Challenge;

import java.util.List;

public abstract class Day11Challenge extends Challenge {

    static final char EMPTY_SEAT = 'L';
    static final char OCCUPIED_SEAT = '#';
    static final char FLOOR = '.';

    Day11Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201211-input-day11ref2.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        return getMessage(solveByInput(inputList));
    }

    protected long solveByInput(List<String> inputList) {
        int occupied = 0;
        int prevOccupied = -1;
        int counter = 0;
//        while (counter < 10) {
        while (occupied != prevOccupied) {
            prevOccupied = occupied;
            occupied = checkOccupiedAndUpdateList(inputList);
            System.out.println(prevOccupied);
            System.out.println(occupied);
            System.out.println();
            counter++;
        }
        return occupied;
    }

    protected abstract int checkOccupiedAndUpdateList(List<String> inputList);

    private String getMessage(long value) {
        return String.format("The number of seats occupied after equilibrium: %d%n", value);
    }
}
