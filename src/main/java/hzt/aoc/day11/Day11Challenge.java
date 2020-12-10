package hzt.aoc.day11;

import hzt.aoc.Challenge;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Day11Challenge extends Challenge {

    Day11Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201211-input-day11.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<Integer> integers = inputList.stream().map(Integer::parseInt).collect(Collectors.toList());
        return getMessage(solveByInput(integers));
    }

    protected abstract long solveByInput(List<Integer> integers);


    abstract String getMessage(long value);
}
