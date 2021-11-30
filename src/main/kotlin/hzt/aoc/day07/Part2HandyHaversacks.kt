package hzt.aoc.day07

class Part2HandyHaversacks : Day07Challenge(
    "part 2",
    "How many individual bags are required inside your single shiny gold bag"
) {
    override fun solveByRules(bags: Map<String, Bag>): Long =
        if (bags.isNotEmpty()) countInnerBagsRecursive(bags, bags[SHINY_GOLD]) - 1 else 0 // We counted the target bag, reduce count by 1.

    private fun countInnerBagsRecursive(bags: Map<String, Bag>, bag: Bag?): Long {
        var accumulator: Long = 1
        accumulator += bag?.childBagColorsToAmounts?.entries?.asSequence()
            ?.map { (name, count) -> count * countInnerBagsRecursive(bags, bags[name]) }
            ?.sum() ?: 0
        return accumulator
    }

    public override fun getMessage(result: String): String = String.format(
        "The number of individual bags required inside the %s bag: %s%n", SHINY_GOLD, result
    )
}
