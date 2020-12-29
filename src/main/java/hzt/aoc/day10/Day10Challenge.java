package hzt.aoc.day10;

import hzt.aoc.Challenge;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Day10Challenge extends Challenge {

    Day10Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201210-input-day10.txt");
    }

    Day10Challenge(String challengeTitle, String description, String inputFilename) {
        super(challengeTitle, description, inputFilename);
    }

    static final int MAX_STEP_APART = 3;


    @Override
    protected String solve(List<String> inputList) {
        List<Integer> list = inputList.stream().filter(s -> !s.isEmpty()).map(Integer::parseInt).sorted().collect(Collectors.toList());
        list.add(0, 0); // add socket jolt value
        list.add(list.get(list.size() - 1) + MAX_STEP_APART); // add built in phone adaptor jolt value
        return String.valueOf(solveByList(list));
    }

    protected abstract Number solveByList(List<Integer> list);


    long calculateTheProductBetweenOneAndThreeDifference(List<Integer> sortedlist) {
        long oneDifference = 0;
        long threeDifference = 0;
        for (int i = 0; i < sortedlist.size() - 1; i++) {
            int difference = sortedlist.get(i + 1) - sortedlist.get(i);
            if (difference == 1) oneDifference++;
            if (difference == MAX_STEP_APART) threeDifference++;
        }
        return oneDifference * threeDifference;
    }

}
