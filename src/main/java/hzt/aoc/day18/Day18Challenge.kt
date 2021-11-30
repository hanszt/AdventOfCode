package hzt.aoc.day18

import hzt.aoc.Challenge
import java.util.*
import kotlin.streams.toList

abstract class Day18Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201218-input-day18.txt") {
    fun calculateAnswer(equationAString: String): Long {
        LOGGER.trace("Input equation: $equationAString")
        return solveEquation(parseEquation(equationAString))
    }

    override fun solve(inputList: List<String>): String {
        val sumAnswers = inputList.stream().map { equationAString: String -> calculateAnswer(equationAString) }
            .reduce { a: Long, b: Long -> java.lang.Long.sum(a, b) }.orElse(0L)
        return getMessage(sumAnswers)
    }

    private fun parseEquation(equationAString: String): List<String> {
        return equationAString.replace("\\s".toRegex(), "")
            .chars().mapToObj(Int::toChar)
            .map(Char::toString)
            .toList()
    }

    private fun solveEquation(elementList: List<String>): Long {
        var mutElementList = elementList
        var result: String
        while (mutElementList.contains("(")) {
            var indexOpenBracket = 0
            val newList: MutableList<String> = ArrayList(mutElementList)
            for (i in mutElementList.indices) {
                if (mutElementList[i] == "(") {
                    indexOpenBracket = i
                } else if (mutElementList[i] == ")") {
                    val subList = mutElementList.subList(indexOpenBracket + 1, i)
                    LOGGER.trace(subList)
                    result = evaluateBetweenParentheses(subList)
                    replaceEquationBySubResult(newList, result, indexOpenBracket, subList.size + 1)
                    newList.removeAt(indexOpenBracket + 1)
                    LOGGER.trace(result)
                    break
                }
            }
            mutElementList = newList
        }
        result = evaluateBetweenParentheses(mutElementList)
        LOGGER.trace(result)
        return result.toLong()
    }

    fun replaceEquationBySubResult(newList: MutableList<String>, subResult: String, index: Int, equationLength: Int) {
        var j = 0
        while (j < equationLength) {
            newList.removeAt(index)
            j++
        }
        newList.add(index, subResult)
    }

    abstract fun evaluateBetweenParentheses(strings: List<String>): String
    fun evaluateInOrder(subEquation: List<String>): String {
        val elementDeque: Deque<String> = ArrayDeque(subEquation)
        var result = ""
        while (!elementDeque.isEmpty()) {
            val num1 = result.ifEmpty { elementDeque.pollFirst() }
            if (elementDeque.size > 1) {
                val operator = elementDeque.pollFirst()
                val num2 = elementDeque.pollFirst()
                val intResult = evaluate(num1.toLong(), operator, num2.toLong())
                result = intResult.toString()
            }
        }
        return result
    }

    fun evaluate(first: Long, operator: String, second: Long): Long {
        return when (operator) {
            "+" -> first + second
            "*" -> first * second
            else -> throw UnsupportedOperationException(
                "Operator $operator is not supported..."
            )
        }
    }

    abstract fun getMessage(value: Long): String
}
