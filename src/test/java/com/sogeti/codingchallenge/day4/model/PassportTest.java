package com.sogeti.codingchallenge.day4.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PassportTest {

    @Test
    void requiredFieldsPresentFalseWhenEmptyPassport() {
        //arrange
        Passport passport = new Passport();

        //act
        //assert
        Assertions.assertFalse(passport.requiredFieldsPresent());
    }

    @Test
    void requiredFieldsPresentTrueWhenRequiredPassportFieldsNotNull() {
        //arrange
        Passport passport = new Passport("", "",
                "", "", "", "", "");
        //act
        //assert
        Assertions.assertTrue(passport.requiredFieldsPresent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1920", "2002", "1989", "1951"})
    void fieldsMeetCriteriaVaryingBirthYear(String birthYear) {
        Passport passport = new Passport("143454654", "2020", "2018",
                birthYear, "171cm", "grn", "#fe2341");
        Assertions.assertTrue(passport.fieldsMeetCriteria());
    }

    @ParameterizedTest
    @ValueSource(strings = {"171cm", "59in", "76in", "150cm", "193cm"})
    void fieldsMeetCriteriaVaryingHeight(String height) {
        Passport passport = new Passport("143454654", "2020", "2018",
                "1989", height, "grn", "#fe2341");
        Assertions.assertTrue(passport.fieldsMeetCriteria());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1910", "5435", "2021", "-3", "2344", "Integer.MAX_VALUE"})
    void fieldsDontMeetCriteria(String birthYear) {
        Passport passport = new Passport("143454654", "2020", "2018",
                birthYear, "171cm", "grn", "#fe2341");
        Assertions.assertFalse(passport.fieldsMeetCriteria());
    }

    @ParameterizedTest
    @ValueSource(strings = {"120cm", "200ft", "234", "123m", "1233", "123cm"})
    void heightFieldDoesntMeetCriteria(String height) {
        Passport passport = new Passport("143454654", "2020", "2018",
                "1989", height, "grn", "#fe2341");
        Assertions.assertFalse(passport.fieldsMeetCriteria());
    }

    @ParameterizedTest
    @ValueSource(strings = {"120cm", "200ft", "234", "123m", "1233", "123cm"})
    void fieldsDontMeetCriteria4(String passWordId) {
        Passport passport = new Passport(passWordId, "2020", "2018",
                "1989", "171cm", "grn", "#fe2341");
        Assertions.assertFalse(passport.fieldsMeetCriteria());
    }

    @ParameterizedTest
    @ValueSource(strings = {"120cm", "200ft", "234", "123m", "1233", "123cm"})
    void fieldsDontMeetCriteria5(String hairColor) {
        Passport passport = new Passport("143454654", "2020", "2018",
                "1989", "171cm", "grn", hairColor);
        Assertions.assertFalse(passport.fieldsMeetCriteria());
    }
}