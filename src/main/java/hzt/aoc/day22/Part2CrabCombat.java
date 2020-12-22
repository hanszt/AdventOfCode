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
        playGame(player1Cards, player2Cards, new HashSet<>(), new HashSet<>());
        Deque<Integer> winningPlayerCards = player1Cards.isEmpty() ? player2Cards : player1Cards;
        return calculateScoreWinningPlayer(winningPlayerCards);
    }



    private Deque<Integer> playGame(Deque<Integer> player1Cards, Deque<Integer> player2Cards, Set<String> configsPlayer1, Set<String> configsPlayer2) {
        int player1TopCard = player1Cards.pop();
        int player2TopCard = player2Cards.pop();
        String curConfigurationPlayer1 = addConfiguration(player1Cards, configsPlayer1);
        String curConfigurationPlayer2 = addConfiguration(player1Cards, configsPlayer2);
        if (configsPlayer1.contains(curConfigurationPlayer1)) {
            player1Cards.addLast(player1TopCard);
            player1Cards.addLast(player2TopCard);
            return player1Cards;
        }
        if (configsPlayer2.contains(curConfigurationPlayer2)) {
            player2Cards.addLast(player2TopCard);
            player2Cards.addLast(player1TopCard);
            return player2Cards;
        }
        if (player1Cards.size() >= player1TopCard && player2Cards.size() >= player2TopCard) {
            Deque<Integer> newPlayer1Cards = makeCopyNextCards(player1Cards, player1TopCard);
            Deque<Integer> newPlayer2Cards = makeCopyNextCards(player2Cards, player2TopCard);
            Deque<Integer> subGameWinnerCards = playGame(newPlayer1Cards, newPlayer2Cards, new HashSet<>(), new HashSet<>());
            return subGameWinnerCards;
        } else {
            if (player1TopCard > player2TopCard) {
                player1Cards.addLast(player1TopCard);
                player1Cards.addLast(player2TopCard);
            } else {
                player2Cards.addLast(player2TopCard);
                player2Cards.addLast(player1TopCard);
            }
            return player1TopCard > player2TopCard ? player1Cards : player2Cards;
        }

    }

    private Deque<Integer> makeCopyNextCards(Deque<Integer> playerCards, int amount) {
        System.out.println("Current cards:");
        playerCards.forEach(System.out::println);
        Deque<Integer> copy = new ArrayDeque<>();
        while (copy.size() < amount) {
            copy.addLast(playerCards.pop());
        }
        System.out.println("Copy cards:");
        copy.forEach(System.out::println);
        return copy;
    }

    private String addConfiguration(Deque<Integer> playerCards, Set<String> configsPlayer) {
        StringBuilder sb = new StringBuilder();
        playerCards.forEach(sb::append);
        configsPlayer.add(sb.toString());
        return sb.toString();
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}
