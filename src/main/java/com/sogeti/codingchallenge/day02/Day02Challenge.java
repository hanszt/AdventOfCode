package com.sogeti.codingchallenge.day02;


import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.day02.model.Policy;

import java.util.List;

public abstract class Day02Challenge extends Challenge {

    /**
     * --- Day 2: Password Philosophy ---
     * <p>
     * Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via toboggan.
     * <p>
     * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day.
     * "Something's wrong with our computers; we can't log in!" You ask if you can take a look.
     * <p>
     * Their password database seems to be a little corrupted:
     * some of the passwords wouldn't have been allowed by the Official Toboggan Corporate Policy that was in effect when they were chosen.
     * <p>
     * To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted database)
     * and the corporate policy when that password was set.
     */

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
        return String.format("%d of the %d passwords are valid%n%s%n", validPasswords, inputList.size(), DOTTED_LINE);

    }
}
