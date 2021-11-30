package hzt.aoc.day05

import hzt.aoc.day05.model.Seat

class Part2BinaryBoarding :
    Day05Challenge("part 2", "What is your seat Id See ChallengeDay5.md part 2 for the assignment. ") {

    override fun calculateResult(seats: List<Seat>): Int {
        var mySeatId = 0
        // flight completely full
        val possibleSeatIds: MutableSet<Int> = HashSet()
        val seatIds = seats.asSequence()
            .map { it.getSeatID(NUMBER_OF_COLUMNS) }
            .toList()
        // Every seat id must be unique
        for (seatId in seatIds) {
            // Seats with id one less and one more than myId are in my list
            if (seatIds.contains(seatId - 2)) {
                val possibleSeatId = seatId + 1
                // My boarding pass is the only missing boarding pass in the list
                if (!seatIds.contains(possibleSeatId)) {
                    possibleSeatIds.add(possibleSeatId)
                }
            }
        }
        val highestSeatIdOnBoardingPass = findHighestSeatID(seatIds)
        for (seatId in possibleSeatIds) {
            // some of the seats at the very front and back of the plane don't exist on this aircraft, they'll be missing from your list.
            // My seat isn't in the very front or back of the plane
            if (seatId < highestSeatIdOnBoardingPass) {
                mySeatId = seatId
            }
        }
        return mySeatId
    }

    override fun getMessage(result: String): String {
        return String.format("The id of my seat is: %s%n", result)
    }
}
