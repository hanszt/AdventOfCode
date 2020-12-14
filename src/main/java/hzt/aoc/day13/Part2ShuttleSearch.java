package hzt.aoc.day13;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Part2ShuttleSearch extends Day13Challenge {

    public Part2ShuttleSearch() {
        super("Shuttle Search part 2",
                "What is the earliest timestamp such that all of the listed bus IDs depart at " +
                        "offsets matching their positions in the list?");
    }


    @Override
    protected Object solve(List<String> inputList) {
        List<String> busNrList = Arrays.asList(inputList.get(1).split(","));
        int highestIndex = busNrList.size() - 1;
        Map<Integer, Integer> listPositionsToBusNrs = new TreeMap<>();
        for (int i = 0; i < busNrList.size(); i++) {
            if (!busNrList.get(i).matches("x")) {
                listPositionsToBusNrs.put(i, Integer.parseInt(busNrList.get(i)));
            }
        }
        System.out.println(listPositionsToBusNrs);
        return getMessage(0);
    }

    @Override
    String getMessage(Number global) {
        return String.format("%s", global);
    }
}
