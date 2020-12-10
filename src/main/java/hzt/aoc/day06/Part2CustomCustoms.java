package hzt.aoc.day06;

import hzt.aoc.day06.model.Group;

import java.util.List;

public class Part2CustomCustoms extends Day06Challenge {

    public Part2CustomCustoms() {

        super("Custom customs part 2", "For each group, " +
                "count the number of questions to which everyone answered 'yes'. What is the sum of those counts?");
    }

    @Override
    protected int calculateResult(List<Group> groups) {
        return groups.stream().map(Group::amountEveryoneAnsweredYes).reduce(0, (acc, cur) -> acc += cur);
    }

    @Override
    protected String getMessage(int result) {
        return String.format("The sum of the counted answers everyone in the group answered 'yes' to is: %d%n", result);
    }
}
