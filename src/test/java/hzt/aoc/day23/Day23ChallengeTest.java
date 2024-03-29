package hzt.aoc.day23;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23ChallengeTest {

    @Test
    void testPart1() {
        final Challenge challenge = new Part1CrabCups();
        challenge.solveChallenge();
        final var answer = challenge.getAnswer();
        assertEquals(69852437, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final Challenge challenge = new Part2CrabCups();
        challenge.solveChallenge();
        final var answer = challenge.getAnswer();
        assertEquals(91408386135L, Long.parseLong(answer));
    }
}
