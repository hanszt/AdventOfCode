package hzt.aoc.day16

class Part1TicketTranslation : Day16Challenge(
    "part 1",
    "Consider the validity of the nearby tickets you scanned. What is your ticket scanning error rate"
) {
    override fun solveByParsedInput(
        fields: List<Field>,
        yourTicketValues: List<Int>,
        nearbyTicketValues: List<List<Int>>
    ): Long {
        val inValidTicketValues = nearbyTicketValues.asSequence()
            .flatMap { obj: List<Int> -> obj.asSequence()}
            .toMutableList()
        inValidTicketValues.removeAll(findValidTicketValues(fields, nearbyTicketValues))
        return inValidTicketValues.sum().toLong()
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}
