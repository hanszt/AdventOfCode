package hzt.aoc.day05

import java.util.stream.Collectors
import hzt.aoc.day05.model.Seat

class Part1BinaryBoarding : Day05Challenge("part 1", "Find the highest seat ID on a boarding pass in the list. ") {

    override fun calculateResult(seats: List<Seat>): Int {
        return findHighestSeatID(seats.stream()
                .map { it.getSeatID(NUMBER_OF_COLUMNS) }
                .collect(Collectors.toList()))
    }

    override fun getMessage(result: String): String {
        return String.format("The highest seat ID is: %s%n", result)
    }
}
