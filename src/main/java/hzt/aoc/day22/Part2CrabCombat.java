package hzt.aoc.day22;

import java.util.*;

public class Part2CrabCombat extends Day22Challenge {

    public Part2CrabCombat() {
        super("part 2",
                "Defend your honor as Raft Captain by playing the small crab in a game of Recursive Combat using the same two decks as before. " +
                        "What is the winning player's score?");
    }

    @Override
    protected long play(Deque<Integer> player1Cards, Deque<Integer> player2Cards) {
        playGame(player1Cards, player2Cards, new HashSet<>());
        Deque<Integer> winningPlayerCards = player1Cards.isEmpty() ? player2Cards : player1Cards;
        return calculateScoreWinningPlayer(winningPlayerCards);
    }

    private Deque<Integer> playGame(Deque<Integer> player1Cards, Deque<Integer> player2Cards, Set<String> configsPlayers) {
        int player1TopCard = player1Cards.pop();
        int player2TopCard = player2Cards.pop();
        String curConfigurationPlayer1 = configurationAsString(player1Cards);
        String curConfigurationPlayer2 = configurationAsString(player2Cards);
        if (configsPlayers.contains(curConfigurationPlayer1)) {
            player1Cards.addLast(player1TopCard);
            player1Cards.addLast(player2TopCard);
            return player1Cards;
        }

        configsPlayers.add(curConfigurationPlayer1);
        if (player1Cards.size() >= player1TopCard && player2Cards.size() >= player2TopCard) {
            Deque<Integer> newPlayer1Cards = makeCopyNextCards(player1Cards, player1TopCard);
            Deque<Integer> newPlayer2Cards = makeCopyNextCards(player2Cards, player2TopCard);
            Deque<Integer> subGameWinnerCards = playGame(newPlayer1Cards, newPlayer2Cards, new HashSet<>());
            return subGameWinnerCards;
        }
        if (player1Cards.size() < player1TopCard || player2Cards.size() < player2TopCard) {
            if (player1TopCard > player2TopCard) {
                player1Cards.addLast(player1TopCard);
                player1Cards.addLast(player2TopCard);
            } else {
                player2Cards.addLast(player2TopCard);
                player2Cards.addLast(player1TopCard);
            }
            return player1TopCard > player2TopCard ? player1Cards : player2Cards;
        }
        return playGame(player1Cards, player2Cards, configsPlayers);
    }

    private Deque<Integer> makeCopyNextCards(Deque<Integer> playerCards, int amount) {
        Deque<Integer> copy = new ArrayDeque<>();
        while (copy.size() < amount) {
            copy.addLast(playerCards.pop());
        }
        return copy;
    }

    private String configurationAsString(Deque<Integer> playerCards) {
        StringBuilder sb = new StringBuilder();
        playerCards.forEach(sb::append);
        return sb.toString();
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}
