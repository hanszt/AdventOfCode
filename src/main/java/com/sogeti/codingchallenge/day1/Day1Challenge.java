package com.sogeti.codingchallenge.day1;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class Day1Challenge extends Challenge {

    static final int SUM_TO_BE_FOUND = 2020;

    protected Day1Challenge(String challenge, String description) {
        super(challenge, description, "20201201-input-day1.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        Set<Integer> integers = inputList.stream().map(Integer::parseInt).collect(Collectors.toSet());
        List<Integer[]> integersThatSumTo2020List = findIntegersListThatSumTo2020(new TreeSet<>(integers));
        return getMessage(integersThatSumTo2020List);
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
            sb.append(result).append(String.format("%n%s%n", DOTTED_LINE));
        }
        return sb.toString();
    }

}
