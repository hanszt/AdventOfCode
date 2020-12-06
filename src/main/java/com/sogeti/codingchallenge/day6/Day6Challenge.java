package com.sogeti.codingchallenge.day6;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController2;
import com.sogeti.codingchallenge.day6.model.Group;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Day6Challenge extends Challenge {

    static final Logger LOGGER = LogManager.getLogger(Day6Challenge.class);

    protected Day6Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

    int yesAnswersSum = 0;

    @Override
    protected List<String> loadInputList() {
        return new IOController2().readInputFileByLine("20201206-input-day6.txt");
    }

    @Override
    protected void solve(List<String> inputByLineList) {
        List<Group> groups = getGroups(inputByLineList);
        calculateResult(groups);
    }

    protected List<Group> getGroups(List<String> inputByLineList) {
        List<Group> groups = new ArrayList<>();
        List<Character> groupAnswers = new ArrayList<>();
        List<List<Character>> personsInGroupAnswers = new ArrayList<>();
        for (String string : inputByLineList) {
            List<Character> personAnswers = new ArrayList<>();
            for (int i = 0; i < string.length(); i++) {
                groupAnswers.add(string.charAt(i));
                personAnswers.add(string.charAt(i));
            }
            personsInGroupAnswers.add(personAnswers);
            if (string.matches("\\s*")) {
                personsInGroupAnswers.remove(personAnswers);
                groups.add(new Group(new ArrayList<>(groupAnswers), new ArrayList<>(personsInGroupAnswers)));
                groupAnswers.clear();
                personsInGroupAnswers.clear();
            }
        }
        return groups;
    }

    protected abstract void calculateResult(List<Group> groups);

    public abstract void printResult();

}
