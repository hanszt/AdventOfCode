package hzt.aoc.day10;

import java.math.BigInteger;
import java.util.List;

public class Part2AdaptorArrayWithoutCaching extends Day10Challenge {

    public Part2AdaptorArrayWithoutCaching() {
        super("Adapter Array part 2 without caching",
                "What is the total number of distinct ways " +
                        "you can arrange the adapters to connect the charging outlet to your device?");
    }

    @Override
    protected Number solveByList(List<Integer> sortedList) {


        return numberOfWaysToCompleteAdaptorChain(sortedList);
    }

    private BigInteger numberOfWaysToCompleteAdaptorChain(List<Integer> sortedList) {
        if (sortedList.size() == 1) return BigInteger.ONE;
        BigInteger arrangements = BigInteger.ZERO;
        int index = 1;
        Integer current = sortedList.get(0); // first index in sorted list
        while (sortedList.size() > index && sortedList.get(index) - current <= MAX_STEP_APART) {
            BigInteger subArrangements = numberOfWaysToCompleteAdaptorChain(sortedList.subList(index, sortedList.size()));
            arrangements = arrangements.add(subArrangements);
            index++;
        }
        return arrangements;
    }

    @Override
    String getMessage(Number number) {
        return String.format("The number of distinct ways to connect your adaptor is: %s%n", number);
    }
}