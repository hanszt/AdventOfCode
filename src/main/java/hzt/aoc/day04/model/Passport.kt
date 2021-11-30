package hzt.aoc.day04.model

import org.apache.log4j.LogManager
import java.util.*

class Passport {

    private var passwordID: String? = null
    private var expirationYear: String? = null
    private var issueYear: String? = null
    private var countryId: String? = null
    private var birthYear: String? = null
    private var height: String? = null
    private var eyeColor: String? = null
    private var hairColor: String? = null

    constructor() : super()
    constructor(
        passwordID: String,
        expirationYear: String,
        issueYear: String,
        birthYear: String,
        height: String,
        eyeColor: String,
        hairColor: String
    ) {
        this.passwordID = passwordID
        this.expirationYear = expirationYear
        this.issueYear = issueYear
        this.birthYear = birthYear
        this.height = height
        this.eyeColor = eyeColor
        this.hairColor = hairColor
    }

    // in part 1, a password is valid when all fields have a value. Only country ID is optional
    fun requiredFieldsPresent(): Boolean {
        val mandatoryPassportFieldsPresent = passwordID != null && expirationYear != null && issueYear != null
        val userFieldsPresent = birthYear != null && height != null && eyeColor != null && hairColor != null
        return mandatoryPassportFieldsPresent && userFieldsPresent
    }

    fun fieldsMeetCriteria(): Boolean {
        return if (requiredFieldsPresent()) {
            val birthYearValid = checkYear(birthYear, LOWEST_BIRTH_YEAR, HIGHEST_BIRTH_YEAR)
            val issueYearValid = checkYear(issueYear, LOWEST_ISSUE_YEAR, HIGHEST_ISSUE_YEAR)
            val expirationYearValid = checkYear(expirationYear, LOWEST_EXPIRATION_YEAR, HIGHEST_EXPIRATION_YEAR)
            val heightValid = checkHeight(height ?: "")
            val hairColorValid = hairColor?.matches(Regex("(#)([0-9a-fA-F]{6})")) == true // a # followed by exactly six characters 0-9 or a-f.
            val eyeColorValid = VALID_EYE_COLORS.contains(eyeColor)
            val passportIdValid = passwordID?.matches(Regex("([0-9]{9})")) == true // a nine-digit number, including leading zeroes.
            LOGGER.trace(
                isValidAsString(birthYearValid, issueYearValid, expirationYearValid, heightValid,
                    hairColorValid, eyeColorValid, passportIdValid
                )
            )
            (passportIdValid && eyeColorValid && hairColorValid
                    && heightValid && expirationYearValid && issueYearValid && birthYearValid)
        } else false
    }

    private fun isValidAsString(
        birthYearValid: Boolean, issueYearValid: Boolean, expirationYearValid: Boolean,
        heightValid: Boolean, hairColorValid: Boolean, eyeColorValid: Boolean, passportIdValid: Boolean
    ): String {
        return """
            birthYearValid=$birthYearValid
            issueYearValid=$issueYearValid
            expirationYearValid=$expirationYearValid
            heightValid=$heightValid
            hairColorValid=$hairColorValid
            eyeColorValid=$eyeColorValid
            passportIdValid=$passportIdValid
            
            """.trimIndent()
    }

    private fun checkHeight(height: String): Boolean {
        return if (height.length >= MINIMUM_HEIGHT_STRING_LENGTH) {
            val value = height.substring(0, height.length - UNIT_LENGTH)
            val unit = height.substring(height.length - UNIT_LENGTH)
            val valueIsNumber = value.matches(Regex("[0-9]+"))
            LOGGER.trace("value is number=$valueIsNumber Value=$value")
            if (valueIsNumber) {
                val heightValue = value.toInt()
                if (unit == "cm") {
                    heightValue in 150..193
                } else if (unit == "in") {
                    heightValue in 59..76
                } else false
            } else false
        } else false
    }

    private fun checkYear(input: String?, lower: Int, upper: Int): Boolean {
        val matchesFourDigits = input?.length == 4 && input.matches(Regex("\\d{4}"))
        return if (matchesFourDigits) {
            val year = input?.toInt() ?: 0
            year in lower..upper
        } else false
    }

    fun setPasswordID(passwordID: String) {
        this.passwordID = passwordID
    }

    fun setExpirationYear(expirationYear: String) {
        this.expirationYear = expirationYear
    }

    fun setIssueYear(issueYear: String) {
        this.issueYear = issueYear
    }

    fun setCountryId(countryId: String) {
        this.countryId = countryId
    }

    fun setBirthYear(birthYear: String) {
        this.birthYear = birthYear
    }

    fun setHeight(height: String) {
        this.height = height
    }

    fun setEyeColor(eyeColor: String) {
        this.eyeColor = eyeColor
    }

    fun setHairColor(hairColor: String) {
        this.hairColor = hairColor
    }

    override fun toString(): String {
        return "Passport{" +
                "passwordID=" + passwordID +
                ", expirationYear=" + expirationYear +
                ", issueYear=" + issueYear +
                ", countryId=" + countryId +
                ", birthYear=" + birthYear +
                ", height=" + height +
                ", eyeColor='" + eyeColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                '}'
    }

    companion object {
        private val LOGGER = LogManager.getLogger(Passport::class.java)
        private const val LOWEST_BIRTH_YEAR = 1920
        private const val HIGHEST_BIRTH_YEAR = 2002
        private const val LOWEST_ISSUE_YEAR = 2010
        private const val HIGHEST_ISSUE_YEAR = 2020
        private const val LOWEST_EXPIRATION_YEAR = 2020
        private const val HIGHEST_EXPIRATION_YEAR = 2030
        private val VALID_EYE_COLORS: Set<String> =
            HashSet(listOf("amb", "amb", "blu", "brn", "gry", "grn", "hzl", "oth"))
        private const val UNIT_LENGTH = 2
        private const val MINIMUM_HEIGHT_STRING_LENGTH = 3
    }
}
