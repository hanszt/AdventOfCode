package hzt.aoc.day25;

import hzt.aoc.Challenge;

import java.util.List;

public abstract class Day25Challenge extends Challenge {

    Day25Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201225-input-day25.txt");
    }

    static final int NUMBER_TO_DIVIDE_BY = 20201227;
    static final int INIT_SUBJECT_NUMBER = 7;

    @Override
    protected String solve(List<String> inputList) {
        long cardPublicKey = Long.parseLong(inputList.get(0));
        long doorPublicKey = Long.parseLong(inputList.get(1));
        return getMessage(solveByInput(cardPublicKey, doorPublicKey));

    }

    protected abstract long solveByInput(long cardPublicKey, long doorPublicKey);


    abstract String getMessage(long value);
}
