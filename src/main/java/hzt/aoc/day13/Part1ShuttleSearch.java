package hzt.aoc.day13;

import hzt.collections.ListX;
import hzt.sequences.Sequence;

import java.util.List;
import java.util.Map;

public class Part1ShuttleSearch extends Day13Challenge {

    public Part1ShuttleSearch() {
        super("part 1",
                "What is the ID of the earliest bus you can take to the airport " +
                        "multiplied by the number of minutes you'll need to wait for that bus?");
    }

    @Override
    protected String solve(List<String> inputList) {
        final var listX = ListX.of(inputList);
        int earliestTimestamp = Integer.parseInt(listX.first());

        Map<Integer, Integer> busNumbersToWaitingTimes = Sequence.of(inputList.get(1).split(","))
                .filterNot(s -> s.matches("x"))
                .map(Integer::parseInt)
                .associateWith(busNr -> busNr - earliestTimestamp % busNr)
                .toMutableMap();

        int timeToWaitForEarliestBus = Integer.MAX_VALUE;
        int busNumberBelongingToSmallest = 0;
        for (Map.Entry<Integer, Integer> entry : busNumbersToWaitingTimes.entrySet()) {
            if (entry.getValue() < timeToWaitForEarliestBus) {
                timeToWaitForEarliestBus = entry.getValue();
                busNumberBelongingToSmallest = entry.getKey();
            }
        }
        return String.valueOf(timeToWaitForEarliestBus * busNumberBelongingToSmallest);
    }

}
