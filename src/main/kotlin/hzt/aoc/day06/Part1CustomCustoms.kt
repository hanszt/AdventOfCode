package hzt.aoc.day06

import hzt.aoc.day06.model.Group

class Part1CustomCustoms : Day06Challenge(
    "part 1", "For each group, count the number of questions to which anyone answered 'yes'. " +
            "What is the sum of those counts. "
) {
    override fun calculateResult(groups: List<Group>) = groups.sumOf(Group::amountAnyoneAnsweredYes)

    override fun getMessage(result: String): String =
        String.format("The sum of the counts in each group to which anyone answered 'yes' is: %s%n", result)
}
