package hzt.aoc.day01;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1ReportRepair().also(Challenge::solveChallenge).getAnswer();
        assertEquals(252724, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2ReportRepair().also(Challenge::solveChallenge).getAnswer();
        assertEquals(276912720, Integer.parseInt(answer));
    }
}
