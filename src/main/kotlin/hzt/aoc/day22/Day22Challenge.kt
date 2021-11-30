package hzt.aoc.day22

import hzt.aoc.Challenge
import java.util.*

abstract class Day22Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201222-input-day22.txt") {

    override fun solve(inputList: List<String>): String {
        val player1Cards: Deque<Int> = ArrayDeque()
        val player2Cards: Deque<Int> = ArrayDeque()
        fillDecksByInputList(inputList, player1Cards, player2Cards)
        return getMessage(play(player1Cards, player2Cards))
    }

    private fun fillDecksByInputList(inputList: List<String>, player1Cards: Deque<Int>, player2Cards: Deque<Int>) {
        try {
            var player = 0
            for (line in inputList) {
                if (line.isNotEmpty() && NUMBER_LENGTH_ONE_OR_MORE.pattern().matches(Regex(line))) {
                    if (player == 1) player1Cards.addLast(line.toInt())
                    if (player == 2) player2Cards.addLast(line.toInt())
                }
                if (line == "Player 1:") player++
                if (line == "Player 2:") player++
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    fun calculateScoreWinningPlayer(winningPlayerCards: Deque<Int>): Long {
        var score: Long = 0
        var counter = 1
        while (!winningPlayerCards.isEmpty()) {
            score += (counter * winningPlayerCards.pollLast()).toLong()
            counter++
        }
        return score
    }

    protected abstract fun play(player1Cards: Deque<Int>, player2Cards: Deque<Int>): Long
    abstract fun getMessage(value: Long): String
}
