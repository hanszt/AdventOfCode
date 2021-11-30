package hzt.aoc.day02

import hzt.aoc.day02.model.Policy

class Part2PasswordPhilosophy : Day02Challenge(
    "part 2",
    "Count the passwords that are valid, see part two of ChallengeDay2.md for the validity rules"
) {
    override fun isValid(password: String, policy: Policy): Boolean {
        var matchesWithPolicyChar = 0
        if (password[policy.lowerBound - 1] == policy.character) matchesWithPolicyChar++
        if (password[policy.upperBound - 1] == policy.character) matchesWithPolicyChar++
        return matchesWithPolicyChar == 1
    }
}
