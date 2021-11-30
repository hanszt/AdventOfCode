package hzt.aoc.day10;

import java.math.BigInteger;
import java.util.List;

public class Part2AdaptorArrayWithoutCaching extends Day10Challenge {

    public Part2AdaptorArrayWithoutCaching() {
        super("part 2 without caching",
                "What is the total number of distinct ways " +
                        "you can arrange the adapters to connect the charging outlet to your device?",
                "20201210-input-day10ref2.txt");
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
    protected String getMessage(String number) {
        return String.format("The number of distinct ways to connect your adaptor is (Only works for small input set): %s%n", number);
    }
}
