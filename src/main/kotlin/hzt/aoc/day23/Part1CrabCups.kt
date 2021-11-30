package hzt.aoc.day23

class Part1CrabCups : Day23Challenge(
    "part 1",
    "Using your labeling, simulate 100 moves. What are the labels on the cups after cup 1"
) {
    override fun calculateAnswer(cups: MutableList<Int>): Long {
        val lowestCupLabel = cups.minOf { it }
        val highestCupLabel = cups.maxOf { it }
        var indexCurrent = 0
        for (i in 0 until NR_OF_MOVES) {
            indexCurrent = makeMove(cups, indexCurrent, lowestCupLabel, highestCupLabel)
        }
        return arrangeInOrder(cups)
    }

    private fun arrangeInOrder(cups: List<Int>): Long {
        val indexCupOne = getIndexByLabel(CUP_ONE_LABEL, cups)
        val stringBuilder = StringBuilder()
        for (i in 0 until cups.size - 1) {
            stringBuilder.append(cups[(indexCupOne + 1 + i) % cups.size])
        }
        return stringBuilder.toString().toLong()
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }

    companion object {
        private const val NR_OF_MOVES = 100
        private const val CUP_ONE_LABEL = 1
    }
}
