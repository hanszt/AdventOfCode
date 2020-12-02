package com.sogeti.codingchallenge.day1;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController1;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class Day1Challenge extends Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Day1Challenge.class);
    static final int SUM_TO_BE_FOUND = 2020;

    public Day1Challenge(String challenge, String description) {
        super(challenge, description);
    }

    private List<Integer[]> integersThatSumTo2020List;

    @Override
    protected List<String> loadInputList() {
        return new IOController1().readInputFileByLine("20201201-input-day1.txt");
    }

    @Override
    protected void solve(List<String> inputList) {
        Set<Integer> integers = inputList.stream().map(Integer::parseInt).collect(Collectors.toSet());
        integersThatSumTo2020List = findIntegersListThatSumTo2020(new TreeSet<>(integers));
    }

    protected abstract List<Integer[]> findIntegersListThatSumTo2020(SortedSet<Integer> integers);

    public void printResult() {
        String message = String.format("output size: %d", integersThatSumTo2020List.size());
        LOGGER.info(message);
        for (Integer[] entries : integersThatSumTo2020List) {
            long product = 1;
            StringBuilder sb = new StringBuilder();
            for (Integer integer : entries) {
                sb.append(integer).append(", ");
                product *= integer;
            }
            String result = String.format("The %d digits from the list that sum to %d are: %s%nThe product of these digits is: %d%n",
                    entries.length, SUM_TO_BE_FOUND, sb, product);
            LOGGER.info(result);
        }
        LOGGER.info(DOTTED_LINE);
    }

}
