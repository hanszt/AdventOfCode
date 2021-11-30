package hzt.aoc.day06

import hzt.aoc.day06.model.Group

class Part2CustomCustoms : Day06Challenge(
    "part 2", "For each group, " +
            "count the number of questions to which everyone answered 'yes'. What is the sum of those counts"
) {
    override fun calculateResult(groups: List<Group>): Int {
        return groups.stream()
            .mapToInt(Group::amountEveryoneAnsweredYes)
            .sum()
    }

    override fun getMessage(result: String): String {
        return String.format("The sum of the counted answers everyone in the group answered 'yes' to is: %s%n", result)
    }
}
