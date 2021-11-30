package hzt.aoc.day07

class Part1HandyHaversacks : Day07Challenge(
    "part 1",
    "What is the number of bag colors that can contain" +
            " at least one shiny gold bag"
) {
    override fun solveByRules(bags: Map<String, Bag>) =
        bags.values.count { hasDescendent(bags, SHINY_GOLD, it) }.toLong()

    private fun hasDescendent(bags: Map<String, Bag>, target: String, bag: Bag?): Boolean =
        bag?.childBagColorsToAmounts?.keys?.any { it == target || hasDescendent(bags, target, bags[it]) } ?: false

    override fun getMessage(result: String): String =
        String.format("The number of bags containing a %s bag at least once: %s%n", SHINY_GOLD, result)
}
