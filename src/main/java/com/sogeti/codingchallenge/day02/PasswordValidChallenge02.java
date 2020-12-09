package com.sogeti.codingchallenge.day02;

import com.sogeti.codingchallenge.day02.model.Policy;

public class PasswordValidChallenge02 extends Day02Challenge {

    public PasswordValidChallenge02() {
        super("Valid passwords part 2",
                "Count the passwords that are valid, see part two of ChallengeDay2.md for the validity rules");
    }

    @Override
    boolean isValid(String password, Policy policy) {
        int matchesWithPolicyChar = 0;
        if (password.charAt(policy.getLowerBound() - 1) == policy.getCharacter()) matchesWithPolicyChar++;
        if (password.charAt(policy.getUpperBound() - 1) == policy.getCharacter()) matchesWithPolicyChar++;
        return matchesWithPolicyChar == 1;
    }

}
