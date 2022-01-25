package hzt.aoc.day20;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20ChallengeTest {

    @Test
    void testPart1() {
        Challenge challenge = new Part1JurassicJigsaw().also(Challenge::solveChallenge);
        final var answer = challenge.getAnswer();
        assertEquals(83775126454273L, Long.parseLong(answer));
    }

    @Test
    void testPart2() {
        Challenge challenge = new Part2JurassicJigsaw().also(Challenge::solveChallenge);
        final var answer = challenge.getAnswer();
        assertEquals(1993, Integer.parseInt(answer));
    }
}
