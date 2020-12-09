package com.sogeti.codingchallenge.day02;

import com.sogeti.codingchallenge.day02.model.Policy;

public class PasswordValidChallenge1 extends Day02Challenge {

    public PasswordValidChallenge1() {
        super("Valid passwords part 1",
                "Count the passwords that are valid, see part one of ChallengeDay2.md for the validity rules");
    }

    boolean isValid(String password, Policy policy) {
        int matchesWithPolicyChar = 0;
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character == policy.getCharacter()) matchesWithPolicyChar++;
        }
        return matchesWithPolicyChar >= policy.getLowerBound() && matchesWithPolicyChar <= policy.getUpperBound();
    }

}
