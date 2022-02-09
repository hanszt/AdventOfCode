package hzt.aoc.day02;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1PasswordPhilosophy().also(Challenge::solveChallenge).getAnswer();
        assertEquals(467, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2PasswordPhilosophy().also(Challenge::solveChallenge).getAnswer();
        assertEquals(441, Integer.parseInt(answer));
    }
}
