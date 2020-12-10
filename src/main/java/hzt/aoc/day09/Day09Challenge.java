package hzt.aoc.day09;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day09Challenge extends Challenge {


    static final int PRE_AMBLE_LENGTH = 25;

    Day09Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected Object solve(List<String> inputList) {
        List<Long> numbers = inputList.stream().map(Long::parseLong).collect(Collectors.toList());
        return getMessage(solveByXmasList(numbers));
    }

    protected abstract long solveByXmasList(List<Long> integers);

    long findFirstNumberNotSumOfTwoIntegersInPreamble(List<Long> longs) {
        long result = 0;
        List<Long> preambleNumbers = new ArrayList<>();
        for (int i = 0; i < longs.size(); i++) {
            if (preambleNumbers.size() < PRE_AMBLE_LENGTH) preambleNumbers.add(longs.get(i));
            else if (preambleNumbers.size() == PRE_AMBLE_LENGTH) {
                boolean isSum = sumOfUniquePairInPreamble(longs.get(i), preambleNumbers);
                if (!isSum) {
                    result = longs.get(i);
                    break;
                }
                preambleNumbers.remove(longs.get(i - PRE_AMBLE_LENGTH));
                preambleNumbers.add(longs.get(i));
            }
        }
        return result;
    }

    private boolean sumOfUniquePairInPreamble(Long current, List<Long> preAmbleNumbers) {
        for (Long number : preAmbleNumbers) {
            long difference = current - number;
            if (preAmbleNumbers.contains(difference) && difference != number) return true;
        }
        return false;
    }

    abstract Object getMessage(long value);

}
