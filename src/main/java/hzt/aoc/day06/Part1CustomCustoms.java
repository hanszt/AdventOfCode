package hzt.aoc.day06;

import hzt.aoc.day06.model.Group;
import hzt.collections.ListX;

import java.util.List;

public class Part1CustomCustoms extends Day06Challenge {

    public Part1CustomCustoms() {
        super("part 1", "For each group, count the number of questions to which anyone answered 'yes'. " +
                "What is the sum of those counts?. ");
    }

    @Override
    protected int calculateResult(List<Group> groups) {
        return ListX.of(groups).fold(0, (acc, a) -> acc += a.amountAnyoneAnsweredYes());
    }

    @Override
    protected String getMessage(String result) {
        return String.format("The sum of the counts in each group to which anyone answered 'yes' is: %s%n", result);
    }
}
