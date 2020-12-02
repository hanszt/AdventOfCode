package com.sogeti.codingchallenge.day1;

import java.util.*;

public class TwoDigitsSumTo2020 extends Day1Challenge {

    public TwoDigitsSumTo2020() {
        super("Coding challenge day 1 part 1", "");
    }

    @Override
    protected List<Integer[]> findIntegersListThatSumTo2020(SortedSet<Integer> integers) {
        List<Integer[]> entriesList = new ArrayList<>();
        for (Integer integer : integers) {
            int difference = SUM_TO_BE_FOUND - integer;
            if (integers.contains(difference)) {
                Integer[] entries = {integer, difference};
                entriesList.add(entries);
                break;
            }
        }
        return entriesList;
    }
}
