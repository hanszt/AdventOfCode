package hzt.aoc.day13

import hzt.aoc.Challenge

abstract class Day13Challenge : Challenge {
    internal constructor(challengeTitle: String, description: String) : super(
        challengeTitle,
        description,
        "20201213-input-day13.txt"
    )

    internal constructor(challengeTitle: String, description: String, inputFileName: String) : super(
        challengeTitle,
        description,
        inputFileName
    )
}
