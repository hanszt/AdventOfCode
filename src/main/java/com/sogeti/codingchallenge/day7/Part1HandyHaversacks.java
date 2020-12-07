package com.sogeti.codingchallenge.day7;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Part1HandyHaversacks extends Day7Challenge {

    public Part1HandyHaversacks() {
        super("Handy Haversacks part 1",
                "What is the number of bag colors that can contain" +
                        " at least one shiny gold bag?");
    }

    //TODO: Make it work
    @Override
    protected void solveByRules(Map<String, Bag> bags) {
        Set<String> colorsContainingShinyGold = new HashSet<>();
        for (Bag bag : bags.values()) {
            for (String childBagColor : bag.childBagColorsToAmounts.keySet()) {
                Bag childBag = bags.get(childBagColor);
            }

        }
        System.out.println(colorsContainingShinyGold.size());
    }

    private int checkBagColor(String bagColor, Map<String, Bag> bags) {
        Bag bag = bags.get(bagColor);
        if (bag.childBagColorsToAmounts.isEmpty()) {
            return 0;
        }
        return 0;
    }

    public void printResult() {
        LOGGER.info(String.format("%d", 0));
    }

}
