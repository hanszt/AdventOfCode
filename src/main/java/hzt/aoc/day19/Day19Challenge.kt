package hzt.aoc.day19;

import hzt.aoc.Challenge;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Day19Challenge extends Challenge {

    static final int START_RULE = 0;

    Day19Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201219-input-day19.txt");
    }

    final Map<Integer, String> rulesAsStringMap = new HashMap<>();
    final Map<Integer, List<List<Integer>>> rulesToSubRules = new HashMap<>();
    final Map<Integer, Character> endChars = new HashMap<>();
    final List<String> messages = new ArrayList<>();

    @Override
    protected String solve(List<String> inputList) {
        parseInputList(inputList);
        return getMessage(countMatches());
    }

    private void parseInputList(List<String> inputList) {
        for (String line : inputList) {
            if (line.matches("[a-b]{2,}")) messages.add(line);
            else if (!line.isEmpty()) {
                addRuleToRulesMaps(line);
            }
        }
    }

    void addRuleToRulesMaps(String line) {
        String[] ruleNrToSubRules = line.split(":");
        int ruleNr = Integer.parseInt(ruleNrToSubRules[0]);
        String subRulesAsString = ruleNrToSubRules[1].strip();
        rulesAsStringMap.put(ruleNr, subRulesAsString);
        if (subRulesAsString.contains("\"")) {
            endChars.put(ruleNr, subRulesAsString.replace("\"", "").charAt(0));
        } else {
            String[] subRulesAsArray = subRulesAsString.split("\\|");
            List<List<Integer>> subRules = Arrays.stream(subRulesAsArray)
                    .map(this::stringToRuleList)
                    .collect(Collectors.toList());
            rulesToSubRules.put(ruleNr, subRules);
        }
    }

    String parsedInputAsString(Map<Integer, List<List<Integer>>> rulesToSubRules, List<String> messages) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%nTargetRules:%n"));
        endChars.forEach((k, v) -> sb.append(k).append("->").append(v).append(String.format("%n")));
        sb.append(String.format("%nRules:%n"));
        rulesToSubRules.forEach((k, v) -> sb.append(k).append("->").append(v).append(String.format("%n")));
        sb.append(String.format("%nMessages:%n"));
        messages.forEach(str -> sb.append(str).append(String.format("%n")));
        return sb.toString();
    }


    private List<Integer> stringToRuleList(String s) {
        return Arrays.stream(s.strip().split("\\s")).map(Integer::parseInt).collect(Collectors.toList());
    }

    protected abstract long countMatches();


    abstract String getMessage(long value);
}
