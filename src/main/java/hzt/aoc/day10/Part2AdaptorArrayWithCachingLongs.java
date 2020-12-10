package hzt.aoc.day10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2AdaptorArrayWithCachingLongs extends Day10Challenge {

    public Part2AdaptorArrayWithCachingLongs() {
        super("Adapter Array part 2 with caching using longs",
                "What is the total number of distinct ways " +
                        "you can arrange the adapters to connect the charging outlet to your device?");
    }

    @Override
    protected Number solveByList(List<Integer> sortedList) {
        return numberOfWaysToCompleteAdaptorChain(sortedList);
    }

    //improves runtime: Allows to skip parts of the branches in the tree to be recursively walk through.,,
    private final Map<String, Long> cache = new HashMap<>();

    private long numberOfWaysToCompleteAdaptorChain(List<Integer> sortedList) {
        if (sortedList.size() == 1) return 1;
        long arrangements = 0;
        int index = 1;
        Integer current = sortedList.get(0);

        while (sortedList.size() > index && sortedList.get(index) - current <= MAX_STEP_APART) {
            List<Integer> subList = sortedList.subList(index, sortedList.size());
            String stringSubList = Arrays.toString(subList.toArray(new Integer[0]));
            if (!cache.containsKey(stringSubList)) {
                long subArrangements = numberOfWaysToCompleteAdaptorChain(subList);
                cache.put(stringSubList, subArrangements);
                arrangements += subArrangements;
            } else arrangements += cache.get(stringSubList);
            index++;
        }
        return arrangements;
    }

    @Override
    String getMessage(Number number) {
        return String.format("The number of distinct ways to connect your adaptor is: %s%n", number);
    }
}
