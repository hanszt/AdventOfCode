package hzt.aoc.day02

import hzt.aoc.day02.model.Policy

class Part1PasswordPhilosophy : Day02Challenge(
    "part 1",
    "Count the passwords that are valid, see part one of ChallengeDay2.md for the validity rules"
) {
    override fun isValid(password: String, policy: Policy): Boolean {
        var matchesWithPolicyChar = 0
        for (i in 0 until password.length) {
            val character = password[i]
            if (character == policy.character) matchesWithPolicyChar++
        }
        return matchesWithPolicyChar >= policy.lowerBound && matchesWithPolicyChar <= policy.upperBound
    }
}
