package hzt.aoc.day22

import java.util.*

class Part1CrabCombat : Day22Challenge(
    "part 1",
    "Play the small crab in a game of Combat using the two decks you just dealt. " +
            "What is the winning player's score"
) {
    override fun play(player1Cards: Deque<Int>, player2Cards: Deque<Int>): Long {
        while (!player1Cards.isEmpty() && !player2Cards.isEmpty()) {
            playRound(player1Cards, player2Cards)
        }
        val winningPlayerCards = if (player1Cards.isEmpty()) player2Cards else player1Cards
        return calculateScoreWinningPlayer(winningPlayerCards)
    }

    private fun playRound(player1Cards: Deque<Int>, player2Cards: Deque<Int>) {
        val player1TopCard = player1Cards.pop()
        val player2TopCard = player2Cards.pop()
        if (player1TopCard > player2TopCard) {
            player1Cards.addLast(player1TopCard)
            player1Cards.addLast(player2TopCard)
        } else {
            player2Cards.addLast(player2TopCard)
            player2Cards.addLast(player1TopCard)
        }
    }

    override fun getMessage(global: Long): String {
        return String.format("%d", global)
    }
}
