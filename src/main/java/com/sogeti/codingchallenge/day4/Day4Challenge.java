package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController2;
import com.sogeti.codingchallenge.day4.model.Passport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Day4Challenge extends Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Day4Challenge.class);

    protected Day4Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

    @Override
    protected List<String> loadInputList() {
        return new IOController2().readInputFileByLine("20201204-input-day4.txt");
    }

    int totalPassportsChecked = 0;

    @Override
    protected void solve(List<String> inputList) {
        List<Passport> passports = getPasswords(inputList);
        calculateResult(passports);
    }


    private List<Passport> getPasswords(List<String> inputByLineList) {
        List<Passport> passportList = new ArrayList<>();
        List<String> passportValues = new ArrayList<>();
        for (String string : inputByLineList) {
            String[] strings = string.split("\\s");
            passportValues.addAll(Arrays.asList(strings));
            if (string.matches("\\s*")) {
                passportValues.remove(string);
                passportList.add(createPassportFromValues(passportValues));
                passportValues.clear();
            }
        }
        return passportList;
    }

    private static final String BIRTH_YEAR = "byr";
    private static final String ISSUE_YEAR = "iyr";
    private static final String EXPIRATION_YEAR = "eyr";
    private static final String HEIGHT = "hgt";
    private static final String HAIR_COLOR = "hcl";
    private static final String EYE_COLOR = "ecl";
    private static final String PASSPORT_ID = "pid";
    private static final String COUNTRY_ID = "cid";

    private Passport createPassportFromValues(List<String> passwordEntries) {
        Passport passport = new Passport();
        StringBuilder sb = new StringBuilder();
        for (String passwordEntry : passwordEntries) {
            String[] keyValue = passwordEntry.split(":");
            String key = keyValue[0];
            String value = keyValue[1];
            sb.append(String.format("Entry{key='%s', value='%s'}", key, value));
            switch (key) {
                case PASSPORT_ID:
                    passport.setPasswordID(value);
                    break;
                case EXPIRATION_YEAR:
                    passport.setExpirationYear(value);
                    break;
                case ISSUE_YEAR:
                    passport.setIssueYear(value);
                    break;
                case COUNTRY_ID:
                    passport.setCountryId(value);
                    break;
                case BIRTH_YEAR:
                    passport.setBirthYear(value);
                    break;
                case HEIGHT:
                    passport.setHeight(value);
                    break;
                case EYE_COLOR:
                    passport.setEyeColor(value);
                    break;
                case HAIR_COLOR:
                    passport.setHairColor(value);
                    break;
                default:
                    LOGGER.trace("No match");
                    break;
            }
            sb.append(String.format("%n"));
        }

        LOGGER.trace(sb.toString());
        return passport;
    }

    protected abstract void calculateResult(List<Passport> passports);

    public abstract void printResult();

}