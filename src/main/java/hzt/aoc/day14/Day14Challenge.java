package hzt.aoc.day14;

import hzt.aoc.Challenge;

public abstract class Day14Challenge extends Challenge {

    Day14Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201214-input-day14.txt");
    }

    static final int BITMASK_LENGTH = 36;

    abstract String getMessage(long value);

}
