package hzt.aoc.day01;

import hzt.aoc.Challenge;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Day01Challenge extends Challenge {

    static final int SUM_TO_BE_FOUND = 2020;

    protected Day01Challenge(String challenge, String description) {
        super(challenge, description, "20201201-input-day1.txt");
    }

    private final List<Integer[]> integersThatSumTo2020List = new ArrayList<>();

    @Override
    protected String solve(List<String> inputList) {
        Set<Integer> integers = inputList.stream().map(Integer::parseInt).collect(Collectors.toSet());
        integersThatSumTo2020List.clear();
        integersThatSumTo2020List.addAll(findIntegersListThatSumTo2020(new TreeSet<>(integers)));
        LOGGER.trace(getMessage(integersThatSumTo2020List));
        return String.valueOf(calculateProduct(integersThatSumTo2020List.get(0)));
    }

    protected abstract List<Integer[]> findIntegersListThatSumTo2020(SortedSet<Integer> integers);

    public String getMessage(List<Integer[]> integersThatSumTo2020List) {
        StringBuilder sb = new StringBuilder();
        String message = String.format("Output size: %d%n", integersThatSumTo2020List.size());
        sb.append(message);
        for (Integer[] entries : integersThatSumTo2020List) {
            StringBuilder isb = new StringBuilder();
            long product = 1;
            for (Integer integer : entries) {
                isb.append(integer).append(", ");
                product *= integer;
            }
            String result = String.format("The %d digits from the list that sum to %d are: %s%nThe product of these digits is: %d%n",
                    entries.length, SUM_TO_BE_FOUND, isb, product);
            sb.append(result);
        }
        return sb.toString();
    }

    private long calculateProduct(Integer[] entries) {
        long product = 1;
        for (Integer integer : entries) {
            product *= integer;
        }
        return product;
    }

    @Override
    protected String getMessage(String message) {
        return getMessage(integersThatSumTo2020List);
    }

}
