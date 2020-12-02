package com.sogeti.codingchallenge.day2;


import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController1;
import com.sogeti.codingchallenge.day2.model.Policy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class Day2Challenge extends Challenge {

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

    static final Logger LOGGER = LogManager.getLogger(Day2Challenge.class);

    private int numberOfPasswords = 0;
    int validPasswords = 0;

    public Day2Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

    @Override
    protected void solve(List<String> inputList) {
        inputList.stream().map(line -> line.split(": ")).forEach(array -> checkPasswordValidity(array[0], array[1]));
    }

    @Override
    protected List<String> loadInputList() {
        return new IOController1().readInputFileByLine("20201202-input-day2.txt");
    }

    void checkPasswordValidity(String string, String password) {
        numberOfPasswords++;
        Policy policy = getPolicyFromString(string);
        countNumberOfValidPasswords(password, policy);
    }

    private Policy getPolicyFromString(String string) {
        char character = string.charAt(string.length() - 1);
        String range = string.substring(0, string.length() - 2);
        String[] lowerAndUpper = range.split("-");
        return new Policy(Integer.parseInt(lowerAndUpper[0]), Integer.parseInt(lowerAndUpper[1]), character);
    }

    abstract void countNumberOfValidPasswords(String password, Policy policy);

    public void printResult() {
        String message = String.format("%d of the %d passwords are valid", validPasswords, numberOfPasswords);
        LOGGER.info(message);
        LOGGER.info(DOTTED_LINE);
    }
}
