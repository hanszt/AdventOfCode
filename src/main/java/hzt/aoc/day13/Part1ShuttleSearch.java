package hzt.aoc.day13;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part1ShuttleSearch extends Day13Challenge {

    public Part1ShuttleSearch() {
        super("part 1",
                "What is the ID of the earliest bus you can take to the airport " +
                        "multiplied by the number of minutes you'll need to wait for that bus?");
    }

    @Override
    protected Object solve(List<String> inputList) {
        int earliestTimestamp = Integer.parseInt(inputList.get(0));
        List<Integer> integers = Arrays.stream(inputList.get(1).split(","))
                .filter(s -> !s.matches("x"))
                .map(Integer::parseInt).collect(Collectors.toList());
        Map<Integer, Integer> busNumbersToWaitingTimes = integers.stream()
                .collect(Collectors.toMap(busNr -> busNr, busNr -> busNr - earliestTimestamp % busNr));
        int timeToWaitForEarliestBus = Integer.MAX_VALUE;
        int busNumberBelongingToSmallest = 0;
        for (Map.Entry<Integer, Integer> entry : busNumbersToWaitingTimes.entrySet()) {
            if (entry.getValue() < timeToWaitForEarliestBus) {
                timeToWaitForEarliestBus = entry.getValue();
                busNumberBelongingToSmallest = entry.getKey();
            }
        }
        return getMessage(timeToWaitForEarliestBus * busNumberBelongingToSmallest);
    }

    @Override
    String getMessage(Number global) {
        return String.format("%s", global);
    }
}
