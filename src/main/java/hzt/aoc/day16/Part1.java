package hzt.aoc.day16;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Part1 extends Day16Challenge {

    public Part1() {
        super("part 1",
                "");
    }

    @Override
    protected long solveByParsedInput(List<Field> fields, List<Integer> yourTicketValues, List<List<Integer>> nearbyTicketValues) {
        List<Integer> inValidTicketValues = nearbyTicketValues.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        inValidTicketValues.removeAll(findValidTicketValues(fields, nearbyTicketValues));
        return inValidTicketValues.stream().reduce(Integer::sum).orElse(0);
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}
