package com.sogeti.codingchallenge.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class TwoDigitsSumTo2020 extends Day1Challenge {

    public TwoDigitsSumTo2020() {
        super("Two digits sum to 2020", "Find the product of the two entries that sum to 2020");
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
