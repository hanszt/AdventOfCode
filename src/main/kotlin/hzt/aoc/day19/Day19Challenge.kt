package hzt.aoc.day19

import hzt.aoc.Challenge
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

abstract class Day19Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201219-input-day19.txt") {
    val rulesAsStringMap: MutableMap<Int, String> = HashMap()
    val rulesToSubRules: MutableMap<Int, List<List<Int>>> = HashMap()
    val endChars: MutableMap<Int, Char> = HashMap()
    val messages: MutableList<String> = ArrayList()
    override fun solve(inputList: List<String>): String {
        parseInputList(inputList)
        return getMessage(countMatches())
    }

    private fun parseInputList(inputList: List<String>) {
        for (line in inputList) {
            if (line.matches(Regex("[a-b]{2,}"))) messages.add(line) else if (line.isNotEmpty()) {
                addRuleToRulesMaps(line)
            }
        }
    }

    fun addRuleToRulesMaps(line: String) {
        val ruleNrToSubRules = line.split(":".toRegex()).toTypedArray()
        val ruleNr = ruleNrToSubRules[0].toInt()
        val subRulesAsString = ruleNrToSubRules[1].trim()
        rulesAsStringMap[ruleNr] = subRulesAsString
        if (subRulesAsString.contains("\"")) {
            endChars[ruleNr] = subRulesAsString.replace("\"", "")[0]
        } else {
            val subRulesAsArray = subRulesAsString.split("\\|".toRegex()).toTypedArray()
            val subRules = Arrays.stream(subRulesAsArray)
                .map { s: String -> stringToRuleList(s) }
                .collect(Collectors.toList())
            rulesToSubRules[ruleNr] = subRules
        }
    }

    fun parsedInputAsString(rulesToSubRules: Map<Int, List<List<Int>>>, messages: List<String>): String {
        val sb = StringBuilder()
        sb.append(String.format("%nTargetRules:%n"))
        endChars.forEach { (k: Int, v: Char) -> sb.append(k).append("->").append(v).append(String.format("%n")) }
        sb.append(String.format("%nRules:%n"))
        rulesToSubRules.forEach { (k: Int, v: List<List<Int>>) ->
            sb.append(k).append("->").append(v).append(String.format("%n"))
        }
        sb.append(String.format("%nMessages:%n"))
        messages.forEach(Consumer { str: String -> sb.append(str).append(String.format("%n")) })
        return sb.toString()
    }

    private fun stringToRuleList(s: String): List<Int> {
        return Arrays.stream(s.trim().split("\\s".toRegex()).toTypedArray()).map(String::toInt)
            .collect(Collectors.toList())
    }

    protected abstract fun countMatches(): Long
    abstract fun getMessage(value: Long): String

    companion object {
        const val START_RULE = 0
    }
}
