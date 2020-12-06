package com.sogeti.codingchallenge.day6;

import com.sogeti.codingchallenge.day6.model.Group;

import java.util.List;

public class Part2CustomCustoms extends Day6Challenge {

    public Part2CustomCustoms() {

        super("Custom customs part 2", "For each group, " +
                "count the number of questions to which everyone answered 'yes'. What is the sum of those counts?");
    }

    @Override
    protected void calculateResult(List<Group> groups) {
        for(Group group : groups) {
            int amountAnsweredYes = group.amountEveryoneAnsweredYes();
            yesAnswersSum += amountAnsweredYes;
        }
    }

    @Override
    public void printResult() {
        LOGGER.info(String.format("The sum of the counted answers everyone in the group answered yes to is: %d", yesAnswersSum));
    }
}
