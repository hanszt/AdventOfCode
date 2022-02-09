package hzt.aoc.day06;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1CustomCustoms().also(Challenge::solveChallenge).getAnswer();
        assertEquals(6416, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2CustomCustoms().also(Challenge::solveChallenge).getAnswer();
        assertEquals(3050, Integer.parseInt(answer));
    }

}
