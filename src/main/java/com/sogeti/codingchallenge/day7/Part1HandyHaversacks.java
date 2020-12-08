package com.sogeti.codingchallenge.day7;

import java.util.Map;

public class Part1HandyHaversacks extends Day7Challenge {

    public Part1HandyHaversacks() {
        super("Handy Haversacks part 1",
                "What is the number of bag colors that can contain" +
                        " at least one shiny gold bag?");
    }

    @Override
    protected long solveByRules(Map<String, Bag> bags) {
        return bags.values().stream().filter(bag -> hasDescendent(bags, SHINY_GOLD, bag)).count();
    }

    private boolean hasDescendent(Map<String, Bag> bags, String target, Bag bag) {
        return bag.childBagColorsToAmounts.keySet().stream()
                .anyMatch(color -> color.equals(target) || hasDescendent(bags, target, bags.get(color)));
    }

    String getMessage(long numberOfBags) {
        return String.format("The number of bags containing a %s bag at least once: %d", SHINY_GOLD, numberOfBags);
    }

}
