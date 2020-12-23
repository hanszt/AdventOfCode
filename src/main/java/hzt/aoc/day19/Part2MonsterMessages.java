package hzt.aoc.day19;

import java.util.regex.Pattern;

// Credits to Johan de Jong
public class Part2MonsterMessages extends Day19Challenge {

    public Part2MonsterMessages() {
        super("part 2",
                "How many messages completely match rule 0? (with loops)");
    }

    @Override
    protected long countMatches() {
        addRuleToRulesMaps("8: 42 | 42 8");
        addRuleToRulesMaps("11: 42 31 | 42 11 31");
        LOGGER.trace(parsedInputAsString(rulesToSubRules, messages));
        String startRule = rulesAsStringMap.get(0);
        String regex = ruleRegex2(startRule);
        Pattern pattern = Pattern.compile(regex);
        return messages.stream().filter(message -> pattern.matcher(message).matches()).count();
    }

    private String ruleRegex2(String rule) {
        return ruleRegex2(rule, 0);
    }

    private String ruleRegex2(String rule, int depth) {
        if (depth > 200) return "x";

        if (rule.startsWith("\"")) {
            return rule.substring(1, 2);
        } else {
            StringBuilder sb = new StringBuilder();
            String[] parts = rule.split(" ");
            for (String part : parts) {
                if ("|".equals(part)) {
                    sb.append('|');
                } else {
                    int subRuleIndex = Integer.parseInt(part);
                    String subRule = rulesAsStringMap.get(subRuleIndex);
                    sb.append('(').append(ruleRegex2(subRule, ++depth)).append(')');
                }
            }
            return sb.toString();
        }
    }

    @Override
    String getMessage(long answer) {
        return String.format("%d", answer);
    }

}
