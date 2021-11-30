package hzt.aoc.day05.model

class Seat(private val boardingPass: String, private val row: Int, private val col: Int) {
    fun getSeatID(numberOfColumns: Int): Int {
        return numberOfColumns * row + col
    }

    override fun toString(): String {
        return "Seat{" +
                "boardingPass='" + boardingPass + '\'' +
                ", row=" + row +
                ", col=" + col +
                '}'
    }
}
