package com.sogeti.codingchallenge.day1;

import java.util.*;

public class ThreeDigitsSumTo2020 extends Day1Challenge {

    public ThreeDigitsSumTo2020() {
        super("Three digits sum to 2020", "Find the product of the three digits that sum to 2020");
    }

    /**
     * The Elves in accounting are thankful for your help;
     * one of them even offers you a starfish coin they had left over from a past vacation.
     * They offer you a second one if you can find three numbers in your expense report that meet the same criteria.
     *
     * Using the above example again, the three entries that sum to 2020 are 979, 366, and 675.
     * Multiplying them together produces the answer, 241861950.
     *
     * In your expense report, what is the product of the three entries that sum to 2020?
    */
    @Override
    protected List<Integer[]> findIntegersListThatSumTo2020(SortedSet<Integer> integers) {
        Set<Integer> usedIntegers = new HashSet<>();
        List<Integer> integerList = new ArrayList<>(integers);
        List<Integer[]> entriesList = new ArrayList<>();
        for (int i= 0; i < integerList.size(); i++) {
            for (int j = i + 1; j < integerList.size(); j++) {
                int difference = SUM_TO_BE_FOUND - integerList.get(i) - integerList.get(j);
                if (!usedIntegers.contains(integerList.get(i)) && !usedIntegers.contains(integerList.get(j))
                        && integers.contains(difference)) {
                    Integer[] threeEntries = {integerList.get(i), integerList.get(j), difference};
                    entriesList.add(threeEntries);
                    usedIntegers.add(integerList.get(i));
                    usedIntegers.add(integerList.get(j));
                    break;
                }
            }
        }
        return entriesList;
    }
}
