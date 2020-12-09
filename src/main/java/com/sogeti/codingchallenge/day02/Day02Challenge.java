package com.sogeti.codingchallenge.day02;


import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.day02.model.Policy;

import java.util.List;

public abstract class Day02Challenge extends Challenge {

    protected Day02Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201202-input-day2.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        long validPasswords = inputList.stream().filter(this::passwordIsValid).count();
        return getMessage(inputList, validPasswords);
    }

    boolean passwordIsValid(String line) {
        String[] array = line.split(": ");
        String string = array[0];
        String password = array[1];
        Policy policy = getPolicyFromString(string);
        return isValid(password, policy);
    }

    abstract boolean isValid(String password, Policy policy);

    private Policy getPolicyFromString(String string) {
        char character = string.charAt(string.length() - 1);
        String range = string.substring(0, string.length() - 2);
        String[] lowerAndUpper = range.split("-");
        return new Policy(Integer.parseInt(lowerAndUpper[0]), Integer.parseInt(lowerAndUpper[1]), character);
    }

    public String getMessage(List<String> inputList, long validPasswords) {
        return String.format("%d of the %d passwords are valid%n", validPasswords, inputList.size());

    }
}
