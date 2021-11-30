package hzt.aoc.day16

import java.util.Arrays
import java.util.ArrayList

// credits to turkey dev
class Part2TicketTranslation : Day16Challenge(
    "part 2",
    """
         Once you work out which field is which, look for the six fields on your ticket that start with the word departure. 
         What do you get if you multiply those six values together
         """.trimIndent()
) {
    override fun solveByParsedInput(
        fields: List<Field>,
        yourTicketValues: List<Int>,
        nearbyTicketValues: List<List<Int>>
    ): Long {
        val possibleMatches = Array(fields.size) { BooleanArray(yourTicketValues.size) }
        for (possibleMatch in possibleMatches) Arrays.fill(possibleMatch, true)
        val validTickets = findValidTickets(fields, nearbyTicketValues)
        for (ticket in validTickets) {
            for (col in ticket.indices) {
                for (row in fields.indices) {
                    if (!fields[row].containsValueInRanges(ticket[col])) {
                        possibleMatches[row][col] = false
                    }
                }
            }
        }
        LOGGER.trace(yourTicketValues)
        iterateUntilUniqueValueForeEachField(possibleMatches)
        if (LOGGER.isTraceEnabled) LOGGER.trace(booleanGrid2DAsString(possibleMatches))
        return getAnswer(possibleMatches, yourTicketValues)
    }

    private fun iterateUntilUniqueValueForeEachField(possibleMatches: Array<BooleanArray>) {
        while (!isDone(possibleMatches)) filterOutUniqueValues(possibleMatches)
    }

    private fun filterOutUniqueValues(possibleMatches: Array<BooleanArray>) {
        for (col in 0 until possibleMatches[0].size) {
            var count = 0
            var index = -1
            for (row in possibleMatches.indices) {
                if (possibleMatches[col][row]) {
                    count++
                    index = row
                }
            }
            if (count == 1) {
                for (i in possibleMatches.indices) {
                    if (i != col) {
                        possibleMatches[i][index] = false
                    }
                }
            }
        }
    }

    private fun isDone(possibleMatches: Array<BooleanArray>): Boolean = possibleMatches
        .map { matches -> matches.count { it } }
        .none { it > 1 }

    private fun findValidTickets(fields: List<Field>, nearbyTickets: List<List<Int>>): List<List<Int>> {
        val validTickets: MutableList<List<Int>> = ArrayList()
        for (nearbyTicket in nearbyTickets) {
            val containsOnlyValidValues = nearbyTicket.all { fieldsContainValue(it, fields) }
            if (containsOnlyValidValues) validTickets.add(nearbyTicket)
        }
        return validTickets
    }

    private fun getAnswer(possibleMatches: Array<BooleanArray>, ourTicketValues: List<Int>): Long {
        var answer: Long = 1
        for (row in 0 until FIRST_SIX_FIELDS) {
            for (col in possibleMatches.indices) {
                if (possibleMatches[row][col]) {
                    val value = ourTicketValues[col]
                    answer *= value.toLong()
                    break
                }
            }
        }
        return answer
    }

    override fun getMessage(value: Long): String = String.format("%d", value)

    companion object {
        private const val FIRST_SIX_FIELDS = 6
    }
}
