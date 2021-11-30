package hzt.aoc.day13

class Part1ShuttleSearch : Day13Challenge(
    "part 1",
    "What is the ID of the earliest bus you can take to the airport " +
            "multiplied by the number of minutes you'll need to wait for that bus"
) {
    override fun solve(inputList: List<String>): String {
        val earliestTimestamp = inputList[0].toInt()
        val integers = sequenceOf(*inputList[1].split(",".toRegex()).toTypedArray())
            .filter { it != "x" }
            .map(String::toInt)
            .toList()
        val busNumbersToWaitingTimes = integers.associateBy({ it }, { it - earliestTimestamp % it} )

        var timeToWaitForEarliestBus = Int.MAX_VALUE
        var busNumberBelongingToSmallest = 0
        for ((busNr, waitingTime) in busNumbersToWaitingTimes) {
            if (waitingTime < timeToWaitForEarliestBus) {
                timeToWaitForEarliestBus = waitingTime
                busNumberBelongingToSmallest = busNr
            }
        }
        return (timeToWaitForEarliestBus * busNumberBelongingToSmallest).toString()
    }
}
