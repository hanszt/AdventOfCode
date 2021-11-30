package hzt.aoc.day04

import hzt.aoc.Challenge
import hzt.aoc.day04.model.Passport
import java.util.*

abstract class Day04Challenge protected constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201204-input-day4.txt") {
    private var passportListSize: Long = 0

    override fun solve(inputList: List<String>): String {
        val passports = getPasswords(inputList)
        passportListSize = passports.size.toLong()
        val validPasswords = calculateResult(passports)
        return validPasswords.toString()
    }

    private fun getPasswords(lines: List<String>): List<Passport> {
        val passportList: MutableList<Passport> = ArrayList()
        val passportValues: MutableList<String> = ArrayList()
        for (string in lines) {
            val strings = string.split("\\s".toRegex()).toTypedArray()
            passportValues.addAll(listOf(*strings))
            if (string.matches(Regex("\\s*"))) {
                passportValues.remove(string)
                passportList.add(createPassportFromValues(passportValues))
                passportValues.clear()
            }
        }
        return passportList
    }

    private fun createPassportFromValues(passwordEntries: List<String>): Passport {
        val passport = Passport()
        val sb = StringBuilder()
        for (passwordEntry in passwordEntries) {
            val keyValue = passwordEntry.split(":".toRegex()).toTypedArray()
            val key = keyValue[0]
            val value = keyValue[1]
            sb.append(String.format("Entry{key='%s', value='%s'}", key, value))
            when (key) {
                PASSPORT_ID -> passport.setPasswordID(value)
                EXPIRATION_YEAR -> passport.setExpirationYear(value)
                ISSUE_YEAR -> passport.setIssueYear(value)
                COUNTRY_ID -> passport.setCountryId(value)
                BIRTH_YEAR -> passport.setBirthYear(value)
                HEIGHT -> passport.setHeight(value)
                EYE_COLOR -> passport.setEyeColor(value)
                HAIR_COLOR -> passport.setHairColor(value)
                else -> LOGGER.trace("No match")
            }
            sb.append(String.format("%n"))
        }
        LOGGER.trace(sb.toString())
        return passport
    }

    protected abstract fun calculateResult(passports: List<Passport>): Long

    override fun getMessage(result: String): String {
        return String.format("The number of valid passports is: %s of %d%n", result, passportListSize)
    }

    companion object {
        private const val BIRTH_YEAR = "byr"
        private const val ISSUE_YEAR = "iyr"
        private const val EXPIRATION_YEAR = "eyr"
        private const val HEIGHT = "hgt"
        private const val HAIR_COLOR = "hcl"
        private const val EYE_COLOR = "ecl"
        private const val PASSPORT_ID = "pid"
        private const val COUNTRY_ID = "cid"
    }
}
