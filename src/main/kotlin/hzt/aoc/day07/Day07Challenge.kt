package hzt.aoc.day07

import hzt.aoc.Challenge

abstract class Day07Challenge protected constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201207-input-day7.txt") {

    override fun solve(inputList: List<String>): String {
        val bagColorsToRule = inputList.asSequence()
            .map(::extractBagFromLine)
            .associateBy(Bag::bagColor) { it }
        val numberOfBags = solveByRules(bagColorsToRule)
        return numberOfBags.toString()
    }

    protected abstract fun solveByRules(bags: Map<String, Bag>): Long

    private fun extractBagFromLine(line: String): Bag {
        val containerToContent = line.split(" bags contain ".toRegex()).toTypedArray()
        val currentBag = Bag(containerToContent[0])
        val content = containerToContent[1]
        if (content != "no other bags.") {
            val rulesAsStrings = content.split(", ".toRegex()).toTypedArray()
            for (string in rulesAsStrings) {
                val stringAmount: String =
                    string.replace(NOT_DIGIT_LENGTH_ONE_OR_MORE.pattern().toRegex(), "")
                val amount = stringAmount.toInt()
                val bagColor: String =
                    string.replace(NUMBER_LENGTH_ONE_OR_MORE.pattern().toRegex(), "")
                        .split(" bag".toRegex()).toTypedArray()[0].trim() // strip white spaces from trailing edges
                currentBag.addColorToAmount(bagColor, amount)
            }
        }
        return currentBag
    }

    class Bag(val bagColor: String) {
        val childBagColorsToAmounts: MutableMap<String, Int> = HashMap()
        fun addColorToAmount(bagColor: String, amount: Int) {
            childBagColorsToAmounts[bagColor] = amount
        }

        override fun toString(): String {
            return "Rule{" +
                    "bagColor='" + bagColor + '\'' +
                    ", childBagColorsToAmounts=" + childBagColorsToAmounts +
                    '}'
        }
    }

    companion object {
        const val SHINY_GOLD = "shiny gold"
    }
}
