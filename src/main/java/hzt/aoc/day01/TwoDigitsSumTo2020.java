package hzt.aoc.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class TwoDigitsSumTo2020 extends Day01Challenge {

    public TwoDigitsSumTo2020() {
        super("Report Repair part 1",
                "Find the product of the two entries that sum to 2020");
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
