package hzt.aoc.day06

import hzt.aoc.Challenge
import hzt.aoc.day06.model.Group

abstract class Day06Challenge protected constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201206-input-day6.txt") {

    override fun solve(inputList: List<String>): String {
        val groups = getGroups(inputList)
        val result = calculateResult(groups)
        return result.toString()
    }

    private fun getGroups(inputByLineList: List<String>): List<Group> {
        val groups: MutableList<Group> = ArrayList()
        val groupAnswers: MutableList<Char> = ArrayList()
        val personsInGroupAnswers: MutableList<List<Char>> = ArrayList()
        for (string in inputByLineList) {
            val personAnswers: MutableList<Char> = ArrayList()
            for (i in string.indices) {
                groupAnswers.add(string[i])
                personAnswers.add(string[i])
            }
            personsInGroupAnswers.add(personAnswers)
            if (string.matches(Regex("\\s*"))) {
                personsInGroupAnswers.remove(personAnswers)
                groups.add(Group(ArrayList(groupAnswers), ArrayList(personsInGroupAnswers)))
                groupAnswers.clear()
                personsInGroupAnswers.clear()
            }
        }
        return groups
    }

    protected abstract fun calculateResult(groups: List<Group>): Int
}
