package hzt.aoc.day07;

import hzt.aoc.Challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Day07Challenge extends Challenge {

    static final String SHINY_GOLD = "shiny gold";

    protected Day07Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201207-input-day7.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        Map<String, Bag> bagColorsToRule = inputList.stream()
                .map(this::extractBagFromLine)
                .collect(Collectors.toMap(bag -> bag.bagColor, bag -> bag));
        long numberOfBags = solveByRules(bagColorsToRule);
        return getMessage(numberOfBags);
    }

    protected abstract long solveByRules(Map<String, Bag> bags);

    Bag extractBagFromLine(String line) {
        String[] containerToContent = line.split(" bags contain ");
        Bag currentBag = new Bag(containerToContent[0]);
        String content = containerToContent[1];
        if (!content.equals("no other bags.")) {
            String[] rulesAsStrings = content.split(", ");
            for (String string : rulesAsStrings) {
                String stringAmount = string.replaceAll(NOT_DIGIT_LENGTH_ONE_OR_MORE.pattern(), "");
                int amount = Integer.parseInt(stringAmount);
                String bagColor = string.replaceAll(NUMBER_LENGTH_ONE_OR_MORE.pattern(), "")
                        .split(" bag")[0].strip(); // strip white spaces from trailing edges
                currentBag.addColorToAmount(bagColor, amount);
            }
        }
        return currentBag;
    }

    abstract String getMessage(long numberOfBags);

    static class Bag {

        final String bagColor;
        final Map<String, Integer> childBagColorsToAmounts = new HashMap<>();

        public Bag(String bagColor) {
            this.bagColor = bagColor;
        }

        void addColorToAmount(String bagColor, int amount) {
            childBagColorsToAmounts.put(bagColor, amount);
        }

        @Override
        public String toString() {
            return "Rule{" +
                    "bagColor='" + bagColor + '\'' +
                    ", childBagColorsToAmounts=" + childBagColorsToAmounts +
                    '}';
        }
    }

}
