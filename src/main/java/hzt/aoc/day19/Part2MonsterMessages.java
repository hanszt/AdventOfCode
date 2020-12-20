package hzt.aoc.day19;

import java.util.ArrayList;
import java.util.List;

public class Part2MonsterMessages extends Day19Challenge {

    public Part2MonsterMessages() {
        super("part 2",
                "How many messages completely match rule 0? (with loops) not working yet...");
    }


    @Override
    protected long countMatches() {
        addRuleToRulesMap(rulesToSubRules, "8: 42 | 42 8");
        addRuleToRulesMap(rulesToSubRules, "11: 42 31 | 42 11 31");
        LOGGER.trace(parsedInputAsString(rulesToSubRules, messages));
        // chars has to be empty to match the same length after going through matches method
        return messages.stream().map(this::asCharList).filter(chars -> matches(chars, START_RULE) && chars.isEmpty()).count();
    }

    private List<Character> asCharList(String message) {
        List<Character> chars = new ArrayList<>();
        for (char c : message.toCharArray()) {
            chars.add(c);
        }
        return chars;
    }

    // requires a mutableList so that's why a list of chars is passed instead of a string
    private boolean matches(List<Character> messageChars, int rule) {
        if (messageChars.isEmpty()) return false;
        if (endChars.containsKey(rule)) return ruleIsEndRule(rule, messageChars);
        List<List<Integer>> allSubRules = rulesToSubRules.get(rule);
        for (List<Integer> subRules : allSubRules) {
            List<Character> charsCopy = new ArrayList<>(messageChars);
            boolean matchesAll = true;
            for (int curRule : subRules) {
                if (!matches(charsCopy, curRule)) {
                    matchesAll = false;
                    break;
                }
            }
            if (matchesAll) {
                while (messageChars.size() > charsCopy.size()) messageChars.remove(0);
                return true;
            }
        }
        return false;
    }

    private boolean ruleIsEndRule(int rule, List<Character> messageChars) {
        if (messageChars.get(0).equals(endChars.get(rule))) {
            messageChars.remove(0);
            return true;
        } else return false;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}
