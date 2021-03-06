package hzt.aoc.day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2RambunctiousRecitation extends Day15Challenge {

    public Part2RambunctiousRecitation() {
        super("part 2",
                "Given your starting numbers, what will be the 30000000th number spoken?");
    }

    private static final int THRESHOLD = 30_000_000;
    private static final int LOG_STEP = 3_000_000;

    @Override
    protected int getNthNumberSpoken(List<Integer> numbers) {
        long start = System.nanoTime();
        int index = 0;
        int last = -1;
        Map<Integer, Integer> seenLastToIndex = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            index++;
            last = numbers.get(i);
            if (i != numbers.size() - 1) seenLastToIndex.put(last, index);
        }
        while (index < THRESHOLD) {
            int seenLastTemp = seenLastToIndex.getOrDefault(last, -1);
            seenLastToIndex.put(last, index);
            last = seenLastTemp == -1 ? 0 : (index - seenLastTemp);
            index++;
            start = logTime(index, LOG_STEP, 0, last, start);
        }
        return last;
    }

    @Override
    protected String getMessage(String global) {
        return String.format("The %dth number spoken is: %s", THRESHOLD, global);
    }
}
