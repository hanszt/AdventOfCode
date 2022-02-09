package hzt.aoc.day07;

import hzt.aoc.Challenge;
import hzt.collections.ListX;
import hzt.collections.MapX;
import hzt.collections.MutableMapX;

import java.util.List;

public abstract class Day07Challenge extends Challenge {

    static final String SHINY_GOLD = "shiny gold";

    protected Day07Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201207-input-day7.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        MapX<String, Bag> bagColorsToRule = ListX.of(inputList)
                .map(this::extractBagFromLine)
                .associateBy(Bag::getBagColor);
        long numberOfBags = solveByRules(bagColorsToRule);
        return String.valueOf(numberOfBags);
    }

    protected abstract long solveByRules(MapX<String, Bag> bags);

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

    static class Bag {

        private final String bagColor;
        private final MutableMapX<String, Integer> childBagColorsToAmounts = MutableMapX.empty();

        public Bag(String bagColor) {
            this.bagColor = bagColor;
        }

        void addColorToAmount(String bagColor, int amount) {
            childBagColorsToAmounts.put(bagColor, amount);
        }

        public String getBagColor() {
            return bagColor;
        }

        public MutableMapX<String, Integer> getChildBagColorsToAmounts() {
            return childBagColorsToAmounts;
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
