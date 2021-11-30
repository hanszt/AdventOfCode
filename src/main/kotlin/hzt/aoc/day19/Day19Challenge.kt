package hzt.aoc.day19

import hzt.aoc.Challenge

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
            val subRules = sequenceOf(*subRulesAsArray)
                .map(::toStringToRuleList)
                .toList()
            rulesToSubRules[ruleNr] = subRules
        }
    }

    fun parsedInputAsString(rulesToSubRules: Map<Int, List<List<Int>>>, messages: List<String>): String {
        val sb = StringBuilder()
        sb.append(String.format("%nTargetRules:%n"))
        endChars.forEach { (int, char) -> sb.append(int).append("->").append(char).append(String.format("%n")) }
        sb.append(String.format("%nRules:%n"))
        rulesToSubRules.forEach { (int, subRules) -> sb.append(int).append("->").append(subRules).append(String.format("%n"))
        }
        sb.append(String.format("%nMessages:%n"))
        messages.forEach { sb.append(it).append(String.format("%n")) }
        return sb.toString()
    }

    private fun toStringToRuleList(s: String): List<Int> = s.trim()
        .split("\\s".toRegex())
        .toList()
        .map(String::toInt)

    protected abstract fun countMatches(): Long

    abstract fun getMessage(value: Long): String

    companion object {
        const val START_RULE = 0
    }
}
