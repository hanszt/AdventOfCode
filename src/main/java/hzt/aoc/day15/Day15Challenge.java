package hzt.aoc.day15;

import hzt.aoc.Challenge;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day15Challenge extends Challenge {

    static final Logger LOGGER = LogManager.getLogger(Day15Challenge.class);

    Day15Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201215-input-day15.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<Integer> numbers = Arrays.stream(inputList.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
        return getMessage(getNthNumberSpoken(numbers));
    }

    long logTime(int counter, int step, int offset, int lastNumberSpoken, long start) {
        if (counter % step == offset) {
            LOGGER.info(String.format("size: %9d, last number: %9d, time to calculate: %3.3f ms",
                    counter, lastNumberSpoken, (System.nanoTime() - start) / 1e6));
            start = System.nanoTime();
        }
        return start;
    }

    protected abstract int getNthNumberSpoken(List<Integer> numbers);


    abstract String getMessage(long value);
}
