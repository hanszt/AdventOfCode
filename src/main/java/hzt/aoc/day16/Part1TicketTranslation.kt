package hzt.aoc.day16

import java.util.stream.Collectors

class Part1TicketTranslation : Day16Challenge(
    "part 1",
    "Consider the validity of the nearby tickets you scanned. What is your ticket scanning error rate"
) {
    override fun solveByParsedInput(
        fields: List<Field>,
        yourTicketValues: List<Int>,
        nearbyTicketValues: List<List<Int>>
    ): Long {
        val inValidTicketValues = nearbyTicketValues.stream()
            .flatMap { obj: List<Int> -> obj.stream() }
            .collect(Collectors.toList())
        inValidTicketValues.removeAll(findValidTicketValues(fields, nearbyTicketValues))
        return inValidTicketValues.stream().reduce { a: Int, b: Int -> Integer.sum(a, b) }.orElse(0)
            .toLong()
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}
