package hzt.aoc.day07;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1HandyHaversacks().also(Challenge::solveChallenge).getAnswer();
        assertEquals(378, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2HandyHaversacks().also(Challenge::solveChallenge).getAnswer();
        assertEquals(27526, Integer.parseInt(answer));
    }
}
