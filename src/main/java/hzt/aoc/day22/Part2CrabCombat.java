package hzt.aoc.day22;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// Credits to Johan de Jong
public class Part2CrabCombat extends Day22Challenge {

    public Part2CrabCombat() {
        super("part 2",
                "Defend your honor as Raft Captain by playing the small crab in a game of " +
                        "Recursive Combat using the same two decks as before. " +
                        "What is the winning player's score?");
    }

    @Override
    protected long play(final Deque<Integer> player1Cards, final Deque<Integer> player2Cards) {
        playRecursiveGame(player1Cards, player2Cards);
        final Deque<Integer> winningPlayerCards = player1Cards.isEmpty() ? player2Cards : player1Cards;
        return calculateScoreWinningPlayer(winningPlayerCards);
    }

    private int playRecursiveGame(final Deque<Integer> player1Cards, final Deque<Integer> player2Cards) {
        final Set<String> configsPlayer1 = new HashSet<>();
        final Set<String> configsPlayer2 = new HashSet<>();

        while (!player1Cards.isEmpty() && !player2Cards.isEmpty()) {
            final String curConfigPlayer1 = configurationAsString(player1Cards);
            final String curConfigPlayer2 = configurationAsString(player2Cards);
            if (configsPlayer1.contains(curConfigPlayer1) || configsPlayer2.contains(curConfigPlayer2)) {
                return 1;
            }
            configsPlayer1.add(curConfigPlayer1);
            configsPlayer2.add(curConfigPlayer2);
            determineWinner(player1Cards, player2Cards);
        }
        return player2Cards.isEmpty() ? 1 : 2;
    }

    private void determineWinner(final Deque<Integer> player1Cards, final Deque<Integer> player2Cards) {
        final int player1TopCard = player1Cards.pop();
        final int player2TopCard = player2Cards.pop();

        final int winner;
        if (player1Cards.size() >= player1TopCard && player2Cards.size() >= player2TopCard) {
            final Deque<Integer> subFirst = new ArrayDeque<>(new ArrayList<>(player1Cards).subList(0, player1TopCard));
            final Deque<Integer> subSecond = new ArrayDeque<>(new ArrayList<>(player2Cards).subList(0, player2TopCard));
            winner = playRecursiveGame(subFirst, subSecond);
        } else {
            winner = player1TopCard > player2TopCard ? 1 : 2;
        }
        if (winner == 1) {
            player1Cards.addLast(player1TopCard);
            player1Cards.addLast(player2TopCard);
        } else {
            player2Cards.addLast(player2TopCard);
            player2Cards.addLast(player1TopCard);
        }
    }

    private static String configurationAsString(final Deque<Integer> playerCards) {
        final StringBuilder sb = new StringBuilder();
        playerCards.forEach(sb::append);
        return sb.toString();
    }

    @Override
    String getMessage(final long global) {
        return String.format("%d", global);
    }

}
