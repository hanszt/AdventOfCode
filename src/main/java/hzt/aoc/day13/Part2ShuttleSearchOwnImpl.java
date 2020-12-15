package hzt.aoc.day13;

import java.math.BigInteger;
import java.util.*;

public class Part2ShuttleSearchOwnImpl extends Day13Challenge {

    public Part2ShuttleSearchOwnImpl() {
        super("part 2",
                "What is the earliest timestamp such that all of the listed bus IDs depart at " +
                        "offsets matching their positions in the list? (This is a brute force implementation. Is too slow)",
                "20201213-input-day13ref2.txt");
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
        BigInteger earliestTimestamp = BigInteger.ZERO;
        while (true) {
            List<Boolean> matches = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : listPositionsToBusNrs.entrySet()) {
                int listPosition = entry.getKey();
                int busNr = entry.getValue();
                BigInteger value = (earliestTimestamp.mod(BigInteger.valueOf(busNr)).add(BigInteger.valueOf((long) listPosition - highestIndex)));
                matches.add(value.equals(BigInteger.ZERO));
            }
            if (!matches.contains(false)) break;
            earliestTimestamp = earliestTimestamp.add(BigInteger.ONE);
        }
        return getMessage(earliestTimestamp.subtract(BigInteger.valueOf(highestIndex)));
    }

    @Override
    String getMessage(Number global) {
        return String.format("%s (only works for small inputs)", global);
    }
}
