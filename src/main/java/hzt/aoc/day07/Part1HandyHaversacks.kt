package hzt.aoc.day07

class Part1HandyHaversacks : Day07Challenge(
    "part 1",
    "What is the number of bag colors that can contain" +
            " at least one shiny gold bag"
) {
    override fun solveByRules(bags: Map<String, Bag>): Long {
        return bags.values.stream()
            .filter { hasDescendent(bags, SHINY_GOLD, it) }
            .count()
    }

    private fun hasDescendent(bags: Map<String, Bag>, target: String, bag: Bag?): Boolean {
        return bag?.childBagColorsToAmounts?.keys?.stream()
            ?.anyMatch { it == target || hasDescendent(bags, target, bags[it]) } ?: false
    }

    override fun getMessage(result: String): String {
        return String.format(
            "The number of bags containing a %s bag at least once: %s%n",
            SHINY_GOLD,
            result
        )
    }
}
