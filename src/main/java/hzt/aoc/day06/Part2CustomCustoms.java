package hzt.aoc.day06;

import hzt.aoc.day06.model.Group;
import hzt.collections.ListX;

import java.util.List;

public class Part2CustomCustoms extends Day06Challenge {

    public Part2CustomCustoms() {
        super("part 2", "For each group, " +
                "count the number of questions to which everyone answered 'yes'. What is the sum of those counts?");
    }

    @Override
    protected int calculateResult(List<Group> groups) {
        return ListX.of(groups).fold(0, (acc, cur) -> acc += cur.amountEveryoneAnsweredYes());
    }

    @Override
    protected String getMessage(String result) {
        return String.format("The sum of the counted answers everyone in the group answered 'yes' to is: %s%n", result);
    }
}
