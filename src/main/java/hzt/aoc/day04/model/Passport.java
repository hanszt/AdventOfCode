package hzt.aoc.day04.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Passport {

    private static final Logger LOGGER = LogManager.getLogger(Passport.class);
    private static final int LOWEST_BIRTH_YEAR = 1920;
    private static final int HIGHEST_BIRTH_YEAR = 2002;
    private static final int LOWEST_ISSUE_YEAR = 2010;
    private static final int HIGHEST_ISSUE_YEAR = 2020;
    private static final int LOWEST_EXPIRATION_YEAR = 2020;
    private static final int HIGHEST_EXPIRATION_YEAR = 2030;
    private static final Set<String> VALID_EYE_COLORS = new HashSet<>(Arrays.asList("amb", "amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
    private static final int UNIT_LENGTH = 2;
    private static final int MINIMUM_HEIGHT_STRING_LENGTH = 3;

    private String passwordID;
    private String expirationYear;
    private String issueYear;
    private String countryId;
    private String birthYear;
    private String height;
    private String eyeColor;
    private String hairColor;

    public Passport() {
        super();
    }

    public Passport(String passwordID, String expirationYear, String issueYear, String birthYear, String height, String eyeColor, String hairColor) {
        this.passwordID = passwordID;
        this.expirationYear = expirationYear;
        this.issueYear = issueYear;
        this.birthYear = birthYear;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    // in part 1, a password is valid when all fields have a value. Only country ID is optional
    public boolean requiredFieldsPresent() {
        boolean mandatoryPassportFieldsPresent = passwordID != null && expirationYear != null && issueYear != null;
        boolean userFieldsPresent = birthYear != null && height != null && eyeColor != null && hairColor != null;
        return mandatoryPassportFieldsPresent && userFieldsPresent;
    }

    public boolean fieldsMeetCriteria() {
        if (requiredFieldsPresent()) {
            boolean birthYearValid = checkYear(birthYear, LOWEST_BIRTH_YEAR, HIGHEST_BIRTH_YEAR);
            boolean issueYearValid = checkYear(issueYear, LOWEST_ISSUE_YEAR, HIGHEST_ISSUE_YEAR);
            boolean expirationYearValid = checkYear(expirationYear, LOWEST_EXPIRATION_YEAR, HIGHEST_EXPIRATION_YEAR);
            boolean heightValid = checkHeight(height);
            boolean hairColorValid = hairColor != null && hairColor.matches("(#)([0-9a-fA-F]{6})"); // a # followed by exactly six characters 0-9 or a-f.
            boolean eyeColorValid = eyeColor != null && VALID_EYE_COLORS.contains(eyeColor);
            boolean passportIdValid = passwordID.matches("([0-9]{9})"); // a nine-digit number, including leading zeroes.
            LOGGER.trace(isValidAsString(birthYearValid, issueYearValid, expirationYearValid, heightValid,
                    hairColorValid, eyeColorValid, passportIdValid));
            return passportIdValid && eyeColorValid && hairColorValid
                    && heightValid && expirationYearValid && issueYearValid && birthYearValid;
        } else return false;
    }

    private String isValidAsString(boolean birthYearValid, boolean issueYearValid, boolean expirationYearValid,
                                   boolean heightValid, boolean hairColorValid, boolean eyeColorValid, boolean passportIdValid) {
        return "birthYearValid=" + birthYearValid +
                "\nissueYearValid=" + issueYearValid +
                "\nexpirationYearValid=" + expirationYearValid +
                "\nheightValid=" + heightValid +
                "\nhairColorValid=" + hairColorValid +
                "\neyeColorValid=" + eyeColorValid +
                "\npassportIdValid=" + passportIdValid + "\n";
    }

    private boolean checkHeight(String height) {
        if (height.length() >= MINIMUM_HEIGHT_STRING_LENGTH) {
            String value = height.substring(0, height.length() - UNIT_LENGTH);
            String unit = height.substring(height.length() - UNIT_LENGTH);
            boolean valueIsNumber = value.matches("[0-9]+");
            LOGGER.trace("value is number=" + valueIsNumber + " Value=" + value);
            if (valueIsNumber) {
                int heightValue = Integer.parseInt(value);
                if (unit.equals("cm")) {
                    return heightValue >= 150 && heightValue <= 193;
                } else if (unit.equals("in")) {
                    return heightValue >= 59 && heightValue <= 76;
                } else return false;
            } else return false;
        } else return false;
    }

    private boolean checkYear(String input, int lower, int upper) {
        boolean mathesFourDigits = input.length() == 4 && input.matches("\\d{4}");
        if (mathesFourDigits) {
            int year = Integer.parseInt(input);
            return year >= lower && year <= upper;
        } else return false;
    }

    public void setPasswordID(String passwordID) {
        this.passwordID = passwordID;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public void setIssueYear(String issueYear) {
        this.issueYear = issueYear;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passwordID=" + passwordID +
                ", expirationYear=" + expirationYear +
                ", issueYear=" + issueYear +
                ", countryId=" + countryId +
                ", birthYear=" + birthYear +
                ", height=" + height +
                ", eyeColor='" + eyeColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                '}';
    }
}
