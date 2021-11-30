package hzt.aoc.day25

import hzt.aoc.Challenge

abstract class Day25Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201225-input-day25.txt") {
    override fun solve(inputList: List<String>): String {
        val cardPublicKey = inputList[0].toLong()
        val doorPublicKey = inputList[1].toLong()
        return getMessage(solveByInput(cardPublicKey, doorPublicKey))
    }

    protected abstract fun solveByInput(cardPublicKey: Long, doorPublicKey: Long): Long
    abstract fun getMessage(value: Long): String

    companion object {
        const val NUMBER_TO_DIVIDE_BY = 20201227
        const val INIT_SUBJECT_NUMBER = 7
    }
}
