package com.sogeti.codingchallenge.day7;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Day7Challenge extends Challenge {

    static final Logger LOGGER = LogManager.getLogger(Day7Challenge.class);

    protected Day7Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

    static final String SHINY_GOLD = "shiny gold";

    @Override
    protected List<String> loadInputList() {
        return new IOController2().readInputFileByLine("20201207-input-day7.txt");
    }

    @Override
    protected void solve(List<String> inputList) {
        Map<String, Bag> bagColorsToRule = inputList.stream()
                .map(this::extractRuleFromLine).peek(LOGGER::info)
                .collect(Collectors.toMap(bag -> bag.bagColor, bag -> bag));
        solveByRules(bagColorsToRule);
    }

    protected abstract void solveByRules(Map<String, Bag> bags);

    Bag extractRuleFromLine(String line) {
        String[] containerToContent = line.split(" bags contain ");
        Bag currentBag = new Bag(containerToContent[0]);
        String content = containerToContent[1];
        if (!content.equals("no other bags.")) {
            String[] rulesAsStrings = content.split(", ");
            for (String string : rulesAsStrings) {
                String digits = string.replaceAll("\\D+", ""); // replace non digits
                int amount = Integer.parseInt(digits);
                String bagColor = string.replaceAll("\\d+", "").split(" bag")[0].strip(); // strip white spaces from trailing edges
                currentBag.addColorToAmount(bagColor, amount);
            }
        }
        return currentBag;
    }

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

    public abstract void printResult();

}
