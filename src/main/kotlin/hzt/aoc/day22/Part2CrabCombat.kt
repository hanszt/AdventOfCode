package hzt.aoc.day22

import java.util.*

// Credits to Johan de Jong
class Part2CrabCombat : Day22Challenge(
    "part 2",
    "Defend your honor as Raft Captain by playing the small crab in a game of Recursive Combat using the same two decks as before. " +
            "What is the winning player's score"
) {
    override fun play(player1Cards: Deque<Int>, player2Cards: Deque<Int>): Long {
        playRecursiveGame(player1Cards, player2Cards)
        val winningPlayerCards: Deque<Int> = if (player1Cards.isEmpty()) player2Cards else player1Cards
        return calculateScoreWinningPlayer(winningPlayerCards)
    }

    private fun playRecursiveGame(player1Cards: Deque<Int>, player2Cards: Deque<Int>): Int {
        val configsPlayer1: MutableSet<String> = HashSet()
        val configsPlayer2: MutableSet<String> = HashSet()
        while (!player1Cards.isEmpty() && !player2Cards.isEmpty()) {
            val curConfigPlayer1 = configurationAsString(player1Cards)
            val curConfigPlayer2 = configurationAsString(player2Cards)
            if (configsPlayer1.contains(curConfigPlayer1) || configsPlayer2.contains(curConfigPlayer2)) {
                return 1
            }
            configsPlayer1.add(curConfigPlayer1)
            configsPlayer2.add(curConfigPlayer2)
            determineWinner(player1Cards, player2Cards)
        }
        return if (player2Cards.isEmpty()) 1 else 2
    }

    private fun determineWinner(player1Cards: Deque<Int>, player2Cards: Deque<Int>) {
        val player1TopCard = player1Cards.pop()
        val player2TopCard = player2Cards.pop()
        val winner: Int
        winner = if (player1Cards.size >= player1TopCard && player2Cards.size >= player2TopCard) {
            val subFirst: Deque<Int> =
                ArrayDeque(ArrayList(player1Cards).subList(0, player1TopCard))
            val subSecond: Deque<Int> =
                ArrayDeque(ArrayList(player2Cards).subList(0, player2TopCard))
            playRecursiveGame(subFirst, subSecond)
        } else {
            if (player1TopCard > player2TopCard) 1 else 2
        }
        if (winner == 1) {
            player1Cards.addLast(player1TopCard)
            player1Cards.addLast(player2TopCard)
        } else {
            player2Cards.addLast(player2TopCard)
            player2Cards.addLast(player1TopCard)
        }
    }

    private fun configurationAsString(playerCards: Deque<Int>): String {
        val sb = StringBuilder()
        playerCards.forEach(sb::append)
        return sb.toString()
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}
