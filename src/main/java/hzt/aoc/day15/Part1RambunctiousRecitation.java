package hzt.aoc.day15;

import java.util.List;

public class Part1RambunctiousRecitation extends Day15Challenge {

    public Part1RambunctiousRecitation() {
        super("part 1",
                "Given your starting numbers, what will be the 2020th number spoken?");
    }

    private static final int THRESHOLD = 2020;

    @Override
    protected int getNthNumberSpoken(List<Integer> numbers) {
        int last = 0;
        long start = System.nanoTime();
        while (numbers.size() < THRESHOLD) {
            int prevLast = numbers.remove(numbers.size() - 1);
            int newLast = 0;
            if (numbers.contains(prevLast)) {
                for (int index = numbers.size() - 1; index >= 0; index--) {
                    if (numbers.get(index) == prevLast) {
                        newLast = numbers.size() - index;
                        break;
                    }
                }
            }
            numbers.add(prevLast);
            last = newLast;
            numbers.add(last);
            start = logTime(numbers.size(), 200, 20, last, start);
        }
        return last;
    }

    @Override
    protected String getMessage(String answer) {
        return String.format("The %dth number spoken is: %s", THRESHOLD, answer);
    }

}
