package hzt.aoc.day19

import java.util.stream.Collectors

// credits to Turkey dev
// We used the principle of bnf's here
class Part1MonsterMessages : Day19Challenge(
    "part 1",
    "How many messages completely match rule 0 (without loops)"
) {
    override fun countMatches(): Long {
        LOGGER.trace(parsedInputAsString(rulesToSubRules, messages))
        return messages.stream()
            .map(::asCharList)
            .filter(this::matches)
            .count()
    }

    private fun asCharList(message: String): MutableList<Char> {
        return message.chars()
            .mapToObj(Int::toChar)
            .collect(Collectors.toList())
    }

    private fun matches(messageChars: MutableList<Char>): Boolean {
        // chars has to be empty to match the same length after going through matches method
        return matches(messageChars, START_RULE) && messageChars.isEmpty()
    }

    // requires a mutableList so that's why a list of chars is passed instead of a string
    private fun matches(messageChars: MutableList<Char>, rule: Int): Boolean {
        if (endChars.containsKey(rule)) return ruleIsEndRule(rule, messageChars)
        val allSubRules = rulesToSubRules[rule]
        if (allSubRules != null) {
            for (subRules in allSubRules) {
                val charsCopy: MutableList<Char> = ArrayList(messageChars)
                var matchesAll = true
                for (curRule in subRules) {
                    if (!matches(charsCopy, curRule)) {
                        matchesAll = false
                        break
                    }
                }
                if (matchesAll) {
                    while (messageChars.size > charsCopy.size) messageChars.removeAt(0)
                    return true
                }
            }
        }
        return false
    }

    private fun ruleIsEndRule(rule: Int, messageChars: MutableList<Char>): Boolean {
        return if (messageChars[0] == endChars[rule]) {
            messageChars.removeAt(0)
            true
        } else false
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}
