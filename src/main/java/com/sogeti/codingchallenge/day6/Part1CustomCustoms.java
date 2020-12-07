package com.sogeti.codingchallenge.day6;

import com.sogeti.codingchallenge.day6.model.Group;

import java.util.List;

public class Part1CustomCustoms extends Day6Challenge {

    public Part1CustomCustoms() {
        super("Custom customs part 1", "For each group, count the number of questions to which anyone answered 'yes'. " +
                "What is the sum of those counts?. ");
    }


    @Override
    protected void calculateResult(List<Group> groups) {
        for (Group group : groups) {
            int amountAnyoneAnsweredYes = group.amountAnyoneAnsweredYes();
            yesAnswersSum += amountAnyoneAnsweredYes;
        }
    }

    public void printResult() {
        LOGGER.info(String.format("The sum of the counts in each group to which anyone answered 'yes' is: %d", yesAnswersSum));
    }

}
