package hzt.aoc.day17;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1ConwayCubes().also(Challenge::solveChallenge).getAnswer();
        assertEquals(252, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2ConwayCubes().also(Challenge::solveChallenge).getAnswer();
        assertEquals(2160, Integer.parseInt(answer));
    }
}
