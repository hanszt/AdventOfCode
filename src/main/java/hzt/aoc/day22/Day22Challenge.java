package hzt.aoc.day22;

import hzt.aoc.Challenge;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public abstract class Day22Challenge extends Challenge {

    Day22Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201222-input-day22.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        Deque<Integer> player1Cards = new ArrayDeque<>();
        Deque<Integer> player2Cards = new ArrayDeque<>();
        fillDecksByInputList(inputList, player1Cards, player2Cards);
        return getMessage(play(player1Cards, player2Cards));
    }

    private void fillDecksByInputList(List<String> inputList, Deque<Integer> player1Cards, Deque<Integer> player2Cards) {
        try {
            int player = 0;
            for (String line : inputList) {
                if (!line.isEmpty() && line.matches("\\d+")) {
                    if (player == 1) player1Cards.addLast(Integer.parseInt(line));
                    if (player == 2) player2Cards.addLast(Integer.parseInt(line));
                }
                if (line.equals("Player 1:")) player++;
                if (line.equals("Player 2:")) player++;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    long calculateScoreWinningPlayer(Deque<Integer> winningPlayerCards) {
        long score = 0;
        int counter = 1;
        while (!winningPlayerCards.isEmpty()) {
            score += counter * winningPlayerCards.pollLast();
            counter++;
        }
        return score;
    }

    protected abstract long play(Deque<Integer> player1Cards, Deque<Integer> player2Cards);


    abstract String getMessage(long value);
}
