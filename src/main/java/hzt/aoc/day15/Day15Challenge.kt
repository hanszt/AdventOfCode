package hzt.aoc.day15

import hzt.aoc.Challenge
import org.apache.log4j.LogManager
import org.apache.log4j.Logger

abstract class Day15Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201215-input-day15ref.txt") {
    override fun solve(inputList: List<String>): String {
        val numbers = commaSeparatedStringToIntegerList(inputList[0])
        return getNthNumberSpoken(numbers).toString()
    }

    fun logTime(counter: Int, step: Int, offset: Int, lastNumberSpoken: Int, start: Long): Long {
        if (counter % step == offset) {
            LOGGER.info(String.format(
                    "size: %9d, last number: %9d, time to calculate: %3.3f ms",
                    counter, lastNumberSpoken, (System.nanoTime() - start) / 1e6)
            )
            return System.nanoTime()
        }
        return start
    }

    protected abstract fun getNthNumberSpoken(numbers: MutableList<Int>): Int

    companion object {
        val LOGGER: Logger = LogManager.getLogger(Day15Challenge::class.java)
    }
}
