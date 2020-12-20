package hzt.aoc.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// credits to Turkey dev
// We used the principle of bnf's here
public class Part1MonsterMessages extends Day19Challenge {

    public Part1MonsterMessages() {
        super("part 1",
                "How many messages completely match rule 0? (without loops)");
    }

    @Override
    protected long countMatches() {
        LOGGER.trace(parsedInputAsString(rulesToSubRules, messages));
        // chars has to be empty to match the same length after going through matches method
        return messages.stream().map(this::asCharList).filter(this::matches).count();
    }

    private List<Character> asCharList(String message) {
        return message.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
    }

    private boolean matches(List<Character> messageChars) {
        return matches(messageChars, START_RULE) && messageChars.isEmpty();
    }
    // requires a mutableList so that's why a list of chars is passed instead of a string
    private boolean matches(List<Character> messageChars, int rule) {
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
