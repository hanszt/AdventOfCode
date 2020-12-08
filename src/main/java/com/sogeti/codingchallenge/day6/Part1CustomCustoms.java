package com.sogeti.codingchallenge.day6;

import com.sogeti.codingchallenge.day6.model.Group;

import java.util.List;

public class Part1CustomCustoms extends Day6Challenge {

    public Part1CustomCustoms() {
        super("Custom customs part 1", "For each group, count the number of questions to which anyone answered 'yes'. " +
                "What is the sum of those counts?. ");
    }

    @Override
    protected int calculateResult(List<Group> groups) {
        return groups.stream().map(Group::amountAnyoneAnsweredYes).reduce(0, (acc, cur) -> acc += cur);
    }

    @Override
    protected String getMessage(int result) {
        return String.format("The sum of the counts in each group to which anyone answered 'yes' is: %d", result);
    }
}
