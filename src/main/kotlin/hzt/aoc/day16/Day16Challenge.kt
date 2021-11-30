package hzt.aoc.day16

import hzt.aoc.Challenge
import hzt.aoc.Pair
import java.util.stream.Collectors

abstract class Day16Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201216-input-day16.txt") {

    override fun solve(inputList: List<String>): String {
        val fields: MutableList<Field> = ArrayList()
        val yourTicketValues: MutableList<Int> = ArrayList()
        val nearbyTicketValues: MutableList<List<Int>> = ArrayList()
        Field.setNext(0)
        var inputPart = 0
        for (s in inputList) {
            if (s.isNotBlank()) {
                if (s == "your ticket:") inputPart++
                if (s == "nearby tickets:") inputPart++
                if (inputPart == 0) addField(s, fields) else if (inputPart == 1 && s != "your ticket:") {
                    yourTicketValues.addAll(commaSeparatedStringToIntegerList(s))
                } else if (inputPart == 2 && s != "nearby tickets:") {
                    nearbyTicketValues.add(commaSeparatedStringToIntegerList(s))
                }
            }
        }
        return getMessage(solveByParsedInput(fields, yourTicketValues, nearbyTicketValues))
    }

    protected abstract fun solveByParsedInput(
        fields: List<Field>,
        yourTicketValues: List<Int>,
        nearbyTicketValues: List<List<Int>>
    ): Long

    private fun addField(s: String, fields: MutableList<Field>) {
        val array = s.split(": ".toRegex()).toTypedArray()
        val field = Field(array[0])
        val ranges = array[1].split(" or ".toRegex()).toTypedArray()
        for (range in ranges) {
            val lowerUpper = range.split("-".toRegex()).toTypedArray()
            val lower = lowerUpper[0].toInt()
            val upper = lowerUpper[1].toInt()
            field.addRange(Pair(lower, upper))
        }
        fields.add(field)
    }

    protected fun findValidTicketValues(fields: List<Field>, nearbyTicketValues: List<List<Int>>): List<Int> {
        return nearbyTicketValues.stream().flatMap { obj: List<Int> -> obj.stream() }
            .filter { value: Int -> fieldsContainValue(value, fields) }
            .collect(Collectors.toList())
    }

    fun fieldsContainValue(value: Int, fields: List<Field>): Boolean {
        return fields.stream().anyMatch { field: Field -> field.containsValueInRanges(value) }
    }

    abstract fun getMessage(value: Long): String
}
