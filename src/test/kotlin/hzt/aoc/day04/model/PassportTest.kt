package hzt.aoc.day04.model

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PassportTest {
    @Test
    fun requiredFieldsPresentFalseWhenEmptyPassport() {
        //arrange
        val passport = Passport()

        //act
        //assert
        assertFalse(passport.requiredFieldsPresent())
    }

    @Test
    fun requiredFieldsPresentTrueWhenRequiredPassportFieldsNotNull() {
        //arrange
        val passport = Passport(
            "", "",
            "", "", "", "", ""
        )
        //act
        //assert
        assertTrue(passport.requiredFieldsPresent())
    }

    @ParameterizedTest
    @ValueSource(strings = ["1920", "2002", "1989", "1951"])
    fun fieldsMeetCriteriaVaryingBirthYear(birthYear: String?) {
        val passport = Passport(
            "143454654", "2020", "2018",
            birthYear!!, "171cm", "grn", "#fe2341"
        )
        assertTrue(passport.fieldsMeetCriteria())
    }

    @ParameterizedTest
    @ValueSource(strings = ["171cm", "59in", "76in", "150cm", "193cm"])
    fun fieldsMeetCriteriaVaryingHeight(height: String?) {
        val passport = Passport(
            "143454654", "2020", "2018",
            "1989", height!!, "grn", "#fe2341"
        )
        assertTrue(passport.fieldsMeetCriteria())
    }

    @ParameterizedTest
    @ValueSource(strings = ["1910", "5435", "2021", "-3", "2344", "Integer.MAX_VALUE"])
    fun fieldsDontMeetCriteria(birthYear: String?) {
        val passport = Passport(
            "143454654", "2020", "2018",
            birthYear!!, "171cm", "grn", "#fe2341"
        )
        assertFalse(passport.fieldsMeetCriteria())
    }

    @ParameterizedTest
    @ValueSource(strings = ["120cm", "200ft", "234", "123m", "1233", "123cm"])
    fun heightFieldDoesntMeetCriteria(height: String?) {
        val passport = Passport(
            "143454654", "2020", "2018",
            "1989", height!!, "grn", "#fe2341"
        )
        assertFalse(passport.fieldsMeetCriteria())
    }

    @ParameterizedTest
    @ValueSource(strings = ["120cm", "200ft", "234", "123m", "1233", "123cm"])
    fun fieldsDontMeetCriteria4(passWordId: String?) {
        val passport = Passport(
            passWordId!!, "2020", "2018",
            "1989", "171cm", "grn", "#fe2341"
        )
        assertFalse(passport.fieldsMeetCriteria())
    }

    @ParameterizedTest
    @ValueSource(strings = ["120cm", "200ft", "234", "123m", "1233", "123cm"])
    fun fieldsDontMeetCriteria5(hairColor: String?) {
        val passport = Passport(
            "143454654", "2020", "2018",
            "1989", "171cm", "grn", hairColor!!
        )
        assertFalse(passport.fieldsMeetCriteria())
    }
}
