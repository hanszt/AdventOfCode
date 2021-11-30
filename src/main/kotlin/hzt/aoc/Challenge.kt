package hzt.aoc

import hzt.Launcher
import hzt.aoc.io.IOController2
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

abstract class Challenge protected constructor(
    val part: String,
    private val description: String,
    private val inputFileName: String
) {

    private var title: String? = null
    var solveTime: Long = 0
        private set
    private var startTimeSolve: ZonedDateTime? = null
    var answer: String = ""
        private set

    fun solveChallenge() {
        LOGGER.info(String.format(
                "%n%s %s%nInput: %s%nChallenge: %s%n%s",
                title, part, inputFileName, description, Launcher.DOTTED_LINE
            )
        )
        val inputList = loadInputList()
        val startTime = System.nanoTime()
        if (inputList.isNotEmpty()) {
            if (answer.isEmpty()) {
                startTimeSolve = ZonedDateTime.now()
                answer = solve(inputList)
                val endTime = System.nanoTime()
                solveTime = endTime - startTime
            }
            logResult(answer)
            val message = String.format(
                "%nSolved at %s%nSolved in %5.5f ms%n",
                startTimeSolve?.format(DateTimeFormatter.ofPattern("HH:mm:ss VV")), solveTime / 1e6
            )
            LOGGER.info(message + Launcher.DOTTED_LINE)
        }
    }

    protected fun commaSeparatedStringToIntegerList(s: String): MutableList<Int> {
        return sequenceOf(*s.split(",".toRegex()).toTypedArray())
            .map(String::toInt)
            .toMutableList()
    }

    protected fun listOfStringListsAsString(listOfStringLists: List<List<String>>): String {
        val sb = StringBuilder()
        sb.append(String.format("%n"))
        for (row in listOfStringLists) {
            for (s in row) {
                sb.append(s).append(", ")
            }
            sb.append(String.format("%n"))
        }
        sb.append(String.format("%n"))
        return sb.toString()
    }

    protected fun booleanGrid2DAsString(grid: List<List<Boolean>>): String {
        val sb = StringBuilder()
        sb.append(String.format("%n"))
        for (row in grid) {
            for (active in row) {
                sb.append(if (active) 1 else 0).append(", ")
            }
            sb.append(String.format("%n"))
        }
        sb.append(String.format("%n"))
        return sb.toString()
    }

    protected fun booleanGrid2DAsString(grid: Array<BooleanArray>): String {
        val sb = StringBuilder()
        sb.append(String.format("%n"))
        for (row in grid) {
            for (active in row) {
                sb.append(if (active) 1 else 0).append(", ")
            }
            sb.append(String.format("%n"))
        }
        sb.append(String.format("%n"))
        return sb.toString()
    }

    private fun loadInputList(): List<String> {
        return IOController2().readInputFileByLine(inputFileName)
    }

    protected abstract fun solve(inputList: List<String>): String

    protected open fun getMessage(result: String): String {
        return result
    }

    private fun logResult(result: String) {
        LOGGER.info(String.format("%s%nAnswer:%n%s", Launcher.DOTTED_LINE, getMessage(result)))
    }

    fun setTitle(title: String): Challenge {
        this.title = title
        return this
    }

    fun clearAnswer() {
        answer = ""
    }

    companion object {
        val LOGGER: Logger = LogManager.getLogger(Challenge::class.java)
        @JvmStatic
        protected val NUMBER_LENGTH_ONE_OR_MORE: Pattern = Pattern.compile("\\d+")
        @JvmStatic
        protected val NOT_DIGIT_LENGTH_ONE_OR_MORE: Pattern = Pattern.compile("\\D+")
    }
}
