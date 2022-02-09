package hzt.aoc.day20;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1JurassicJigsaw().also(Challenge::solveChallenge).getAnswer();
        assertEquals(83775126454273L, Long.parseLong(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2JurassicJigsaw().also(Challenge::solveChallenge).getAnswer();
        assertEquals(1993, Integer.parseInt(answer));
    }
}
