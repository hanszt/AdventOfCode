package hzt.aoc.day07;

import hzt.collections.MapX;

public class Part1HandyHaversacks extends Day07Challenge {

    public Part1HandyHaversacks() {
        super("part 1",
                "What is the number of bag colors that can contain" +
                        " at least one shiny gold bag?");
    }

    @Override
    protected long solveByRules(MapX<String, Bag> bags) {
        return bags.values().count(bag -> hasDescendent(bags, SHINY_GOLD, bag));
    }

    private boolean hasDescendent(MapX<String, Bag> bags, String target, Bag bag) {
        return bag.getChildBagColorsToAmounts().keySet()
                .any(color -> color.equals(target) || hasDescendent(bags, target, bags.get(color)));
    }
@Override
    protected String getMessage(String numberOfBags) {
        return String.format("The number of bags containing a %s bag at least once: %s%n", SHINY_GOLD, numberOfBags);
    }

}
