package hzt.aoc.day02

import hzt.aoc.Challenge
import hzt.aoc.day02.model.Policy

abstract class Day02Challenge protected constructor(part: String, description: String) :
    Challenge(part, description, "20201202-input-day2.txt") {
    private var inputListSize: Long = 0

    override fun solve(inputList: List<String>): String {
        inputListSize = inputList.size.toLong()
        return inputList.count(::passwordIsValid).toString()
    }

    private fun passwordIsValid(line: String): Boolean {
        val array = line.split(": ".toRegex()).toTypedArray()
        val string = array[0]
        val password = array[1]
        val policy = getPolicyFromString(string)
        return isValid(password, policy)
    }

    abstract fun isValid(password: String, policy: Policy): Boolean
    private fun getPolicyFromString(string: String): Policy {
        val character = string[string.length - 1]
        val range = string.substring(0, string.length - 2)
        val lowerAndUpper = range.split("-".toRegex()).toTypedArray()
        return Policy(lowerAndUpper[0].toInt(), lowerAndUpper[1].toInt(), character)
    }

    override fun getMessage(result: String): String {
        return String.format("%s of the %d passwords are valid%n", result, inputListSize)
    }
}
