package hzt.aoc.day11

class Part1SeatingSystem : Day11Challenge(
    "part 1",
    "Simulate your seating area by applying the seating rules repeatedly until no seats change state. " +
            "How many seats end up occupied",
    "20201211-input-day11.txt"
) {
    override fun solve(inputList: List<String>): String {
        var occupied = 0
        var prevOccupied = -1
        while (occupied != prevOccupied) {
            prevOccupied = occupied
            occupied = checkOccupiedAndUpdateList(ArrayList(inputList))
        }
        return occupied.toString()
    }

    private fun checkOccupiedAndUpdateList(inputList: MutableList<String>): Int {
        var occupied = 0
        val newList: MutableList<String> = ArrayList()
        for (row in inputList.indices) {
            val curRow = inputList[row]
            val upperRow = if (row > 0) inputList[row - 1] else ""
            val lowerRow = if (row < inputList.size - 1) inputList[row + 1] else ""
            occupied += checkAndUpdateRow(upperRow, curRow, lowerRow, newList)
        }
        inputList.clear()
        inputList.addAll(newList)
        return occupied
    }

    private fun checkAndUpdateRow(
        upperRow: String,
        curRow: String,
        lowerRow: String,
        newList: MutableList<String>
    ): Int {
        var occupied = 0
        val charsNewRow = curRow.toCharArray()
        for (col in charsNewRow.indices) {
            val neighbours = extractNeighBours(upperRow, curRow, lowerRow, col)
            var occupiedNeighbours = 0
            for (c in neighbours.toCharArray()) if (c == OCCUPIED_SEAT) occupiedNeighbours++
            if (charsNewRow[col] == OCCUPIED_SEAT && occupiedNeighbours >= THRESHOLD_BECOMES_EMPTY) {
                charsNewRow[col] = EMPTY_SEAT
            }
            if (charsNewRow[col] == EMPTY_SEAT && !neighbours.contains(OCCUPIED_SEAT.toString())) {
                charsNewRow[col] = OCCUPIED_SEAT
            }
            if (charsNewRow[col] == OCCUPIED_SEAT) occupied++
        }
        newList.add(String(charsNewRow))
        return occupied
    }

    private fun extractNeighBours(upperRow: String, curRow: String, lowerRow: String, col: Int): String {
        val upperNeighBours = extractNeighboursByRow(upperRow, col)
        val lowerNeighBours = extractNeighboursByRow(lowerRow, col)
        val leftNeighBour = curRow.substring(if (col > 0) col - 1 else col, col)
        val rightNeighBour = curRow.substring(
            if (col < curRow.length - 1) col + 1 else col,
            if (col < curRow.length - 1) col + 2 else col
        )
        return upperNeighBours + lowerNeighBours + leftNeighBour + rightNeighBour
    }

    private fun extractNeighboursByRow(row: String, col: Int): String {
        val startIndex = if (col > 0) col - 1 else col
        val endIndex = if (col < row.length - 1) col + 2 else col + 1
        return if (row.isNotEmpty()) row.substring(startIndex, endIndex) else ""
    }

    companion object {
        private const val THRESHOLD_BECOMES_EMPTY = 4
    }
}
