package hzt.aoc.day02;


import hzt.aoc.Challenge;
import hzt.aoc.day02.model.Policy;
import hzt.collections.ListX;

import java.util.List;

public abstract class Day02Challenge extends Challenge {

    protected Day02Challenge(String part, String description) {
        super(part, description, "20201202-input-day2.txt");
    }

    private long inputListSize;

    @Override
    protected String solve(List<String> inputList) {
        inputListSize = inputList.size();
        long validPasswords = ListX.of(inputList).count(this::passwordIsValid);
        return String.valueOf(validPasswords);
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

   protected String getMessage(String validPasswords) {
        return String.format("%s of the %d passwords are valid%n", validPasswords, inputListSize);

    }
}
