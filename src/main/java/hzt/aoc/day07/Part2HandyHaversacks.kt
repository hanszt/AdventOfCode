package hzt.aoc.day07;

import java.util.Map;

public class Part2HandyHaversacks extends Day07Challenge {

    public Part2HandyHaversacks() {
        super("part 2",
                "How many individual bags are required inside your single shiny gold bag?");
    }

    @Override
    protected long solveByRules(Map<String, Bag> bags) {
        return !bags.isEmpty() ? countInnerBagsRecursive(bags, bags.get(SHINY_GOLD)) - 1 : 0; // We counted the target bag, reduce count by 1.
    }

    private long countInnerBagsRecursive(Map<String, Bag> bags, Bag bag) {
        long accumulator = 1;
        accumulator += bag.childBagColorsToAmounts.entrySet().stream()
                .mapToLong(entry -> entry.getValue() * countInnerBagsRecursive(bags, bags.get(entry.getKey()))).sum();
        return accumulator;
    }

    @Override
    public String getMessage(String numberOfBags) {
        return String.format("The number of individual bags required inside the %s bag: %s%n", SHINY_GOLD, numberOfBags);
    }
}
