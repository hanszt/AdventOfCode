package hzt.aoc.day11;

import hzt.aoc.Challenge;

public abstract class Day11Challenge extends Challenge {

    static final char EMPTY_SEAT = 'L';
    static final char OCCUPIED_SEAT = '#';
    static final char FLOOR = '.';

    Day11Challenge(String challengeTitle, String description, String inputFileName) {
        super(challengeTitle, description, inputFileName);
    }

    String getMessage(long value) {
        return String.format("The number of seats occupied after equilibrium: %d%n", value);
    }
}
