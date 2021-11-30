package hzt.aoc.day23

import hzt.aoc.Challenge

abstract class Day23Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201223-input-day23.txt") {
    override fun solve(inputList: List<String>): String {
        val integers = inputList[0].asSequence()
            .map { Character.getNumericValue(it) }
            .toMutableList()
        return getMessage(calculateAnswer(integers))
    }

    protected abstract fun calculateAnswer(cups: MutableList<Int>): Long
    fun makeMove(cups: MutableList<Int>, indexCurrent: Int, lowestCupLabel: Int, highestCupLabel: Int): Int {
        var index = indexCurrent
        val currentCupLabel = cups[index]
        val threePickedUpCups = listPickedUpCups(index, cups)
        cups.removeAll(threePickedUpCups)
        val targetCupLabel = determineTargetCupLabel(currentCupLabel, lowestCupLabel, highestCupLabel, cups)
        val targetIndex = getIndexByLabel(targetCupLabel, cups)
        cups.addAll(targetIndex + 1, threePickedUpCups)
        index = getIndexByLabel(currentCupLabel, cups)
        return if (index + 1 < cups.size) index + 1 else 0
    }

    private fun determineTargetCupLabel(currentCupLabel: Int, lowestCupLabel: Int, highestCupLabel: Int, cups: List<Int>): Int {
        var targetCupLabel = currentCupLabel - 1
        while (!cups.contains(targetCupLabel)) {
            targetCupLabel--
            if (targetCupLabel < lowestCupLabel) {
                targetCupLabel = highestCupLabel
            }
        }
        return targetCupLabel
    }

    private fun listPickedUpCups(indexCurrent: Int, cups: List<Int>): List<Int> {
        val pickedUpCups: MutableList<Int> = ArrayList()
        var indexNext = indexCurrent + 1
        while (indexNext <= indexCurrent + CUPS_PICKED_UP) {
            pickedUpCups.add(cups[indexNext % cups.size])
            indexNext++
        }
        return pickedUpCups
    }

    fun getIndexByLabel(label: Int, cups: List<Int>): Int {
        var index = 0
        for (i in cups.indices) {
            if (cups[i] == label) {
                index = i
                break
            }
        }
        return index
    }

    abstract fun getMessage(value: Long): String

    companion object {
        private const val CUPS_PICKED_UP = 3
    }
}
