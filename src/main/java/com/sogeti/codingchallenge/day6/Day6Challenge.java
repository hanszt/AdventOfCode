package com.sogeti.codingchallenge.day6;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.day6.model.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class Day6Challenge extends Challenge {

    protected Day6Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201206-input-day6.txt");
    }

    @Override
    protected String solve(List<String> inputByLineList) {
        List<Group> groups = getGroups(inputByLineList);
        int result = calculateResult(groups);
        return getMessage(result);
    }

    protected abstract String getMessage(int result);

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

    protected abstract int calculateResult(List<Group> groups);

}
