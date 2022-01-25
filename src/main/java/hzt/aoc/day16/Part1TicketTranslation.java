package hzt.aoc.day16;

import hzt.collections.ListX;

import java.util.List;

public class Part1TicketTranslation extends Day16Challenge {

    public Part1TicketTranslation() {
        super("part 1",
                "Consider the validity of the nearby tickets you scanned. What is your ticket scanning error rate?");
    }

    @Override
    protected long solveByParsedInput(List<Field> fields, List<Integer> yourTicketValues, ListX<ListX<Integer>> nearbyTicketValues) {
        var inValidTicketValues = nearbyTicketValues.flatMapToMutableListOf(ListX::toList);
        inValidTicketValues.removeAll(findValidTicketValues(fields, nearbyTicketValues));
        return inValidTicketValues.sumOf(Integer::longValue);
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}
