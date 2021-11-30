package hzt.aoc.day19

import java.util.regex.Pattern

// Credits to Johan de Jong
class Part2MonsterMessages : Day19Challenge(
    "part 2",
    "How many messages completely match rule 0 (with loops)"
) {
    override fun countMatches(): Long {
        addRuleToRulesMaps("8: 42 | 42 8")
        addRuleToRulesMaps("11: 42 31 | 42 11 31")
        LOGGER.trace(parsedInputAsString(rulesToSubRules, messages))
        val startRule = rulesAsStringMap[0]
        val regex = ruleRegex2(startRule ?: "")
        val pattern = Pattern.compile(regex)
        return messages.stream().filter { message: String -> pattern.matcher(message).matches() }.count()
    }

    private fun ruleRegex2(rule: String, depth: Int = 0): String {
        var mutDepth = depth
        if (mutDepth > 200) return "x"
        return if (rule.startsWith("\"")) {
            rule.substring(1, 2)
        } else {
            val sb = StringBuilder()
            val parts = rule.split(" ".toRegex()).toTypedArray()
            for (part in parts) {
                if ("|" == part) {
                    sb.append('|')
                } else {
                    val subRuleIndex = part.toInt()
                    val subRule = rulesAsStringMap[subRuleIndex]
                    sb.append('(').append(ruleRegex2(subRule ?: "", ++mutDepth)).append(')')
                }
            }
            sb.toString()
        }
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}
