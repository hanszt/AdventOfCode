package hzt.aoc.day07;

import hzt.collections.MapX;

public class Part2HandyHaversacks extends Day07Challenge {

    public Part2HandyHaversacks() {
        super("part 2",
                "How many individual bags are required inside your single shiny gold bag?");
    }

    @Override
    protected long solveByRules(MapX<String, Bag> bags) {
        // We counted the target bag, reduce count by 1.
        return bags.isNotEmpty() ? (countInnerBagsRecursive(bags, bags.get(SHINY_GOLD)) - 1) : 0;
    }

    private static long countInnerBagsRecursive(MapX<String, Bag> bags, Bag bag) {
        return bag.getChildBagColorsToAmounts().entrySet()
                .sumOfLongs(entry -> entry.getValue() * countInnerBagsRecursive(bags, bags.get(entry.getKey()))) + 1;
    }

    @Override
    public String getMessage(String numberOfBags) {
        return String.format("The number of individual bags required inside the %s bag: %s%n", SHINY_GOLD, numberOfBags);
    }
}
