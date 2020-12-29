package hzt.aoc.day04;

import hzt.aoc.Challenge;
import hzt.aoc.day04.model.Passport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Day04Challenge extends Challenge {

    private static final String BIRTH_YEAR = "byr";
    private static final String ISSUE_YEAR = "iyr";
    private static final String EXPIRATION_YEAR = "eyr";
    private static final String HEIGHT = "hgt";
    private static final String HAIR_COLOR = "hcl";
    private static final String EYE_COLOR = "ecl";
    private static final String PASSPORT_ID = "pid";
    private static final String COUNTRY_ID = "cid";
    protected Day04Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201204-input-day4.txt");
    }

    private long passportListSize;

    @Override
    protected String solve(List<String> inputList) {
        List<Passport> passports = getPasswords(inputList);
        passportListSize = passports.size();
        long validPasswords = calculateResult(passports);
        return String.valueOf(validPasswords);
    }

    private List<Passport> getPasswords(List<String> lines) {
        List<Passport> passportList = new ArrayList<>();
        List<String> passportValues = new ArrayList<>();
        for (String string : lines) {
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

    protected abstract long calculateResult(List<Passport> passports);

    public String getMessage(String validPassports) {
        return String.format("The number of valid passports is: %s of %d%n", validPassports, passportListSize);
    }

}
