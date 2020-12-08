package com.sogeti.codingchallenge.day7;

import java.util.Map;

public class Part2HandyHaversacks extends Day7Challenge {

    public Part2HandyHaversacks() {
        super("Handy Haversacks part 2",
                "How many individual bags are required inside your single shiny gold bag?");
    }


    @Override
    protected long solveByRules(Map<String, Bag> bags) {
        return countInnerBagsRecursive(bags, bags.get(SHINY_GOLD)) - 1; // We counted the target bag, reduce count by 1.
    }

    private long countInnerBagsRecursive(Map<String, Bag> bags, Bag bag) {
        long accumulator = 1;
        for (Map.Entry<String, Integer> entry : bag.childBagColorsToAmounts.entrySet()) {
            accumulator += entry.getValue() * countInnerBagsRecursive(bags, bags.get(entry.getKey()));
        }
        return accumulator;
    }

    @Override
    public String getMessage(long numberOfBags) {
        return String.format("The number of individual bags required inside the %s bag: %d", SHINY_GOLD, numberOfBags);
    }
}
