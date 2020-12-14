package hzt.aoc.day13;

import hzt.aoc.Challenge;

public abstract class Day13Challenge extends Challenge {

    Day13Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201213-input-day13.txt");
    }

    Day13Challenge(String challengeTitle, String description, String inputFileName) {
        super(challengeTitle, description, inputFileName);
    }



    abstract String getMessage(Number value);
}
