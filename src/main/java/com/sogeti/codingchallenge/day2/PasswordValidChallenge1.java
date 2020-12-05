package com.sogeti.codingchallenge.day2;

import com.sogeti.codingchallenge.day2.model.Policy;

public class PasswordValidChallenge1 extends Day2Challenge {

    /**
     * Finding valide passwords
     * <p>
     * For example, suppose you have the following list:
     * <p>
     * 1-3 a: abcde
     * 1-3 b: cdefg
     * 2-9 c: ccccccccc
     * <p>
     * Each line gives the password policy and then the password.
     * The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
     * For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
     * <p>
     * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but needs at least 1.
     * The first and third passwords are valid: they contain one a or nine c, both within the limits of their respective policies.
     * <p>
     * How many passwords are valid according to their policies?
     */

    public PasswordValidChallenge1() {
        super("Valid passwords part 1","Count the passwords that are valid, see part one of ChallengeDay2.md for the validity rules");
    }

    void countNumberOfValidPasswords(String password, Policy policy) {
        int matchesWithPolicyChar = 0;
        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);
            if (character == policy.getCharacter()) matchesWithPolicyChar++;
        }
        boolean valid = matchesWithPolicyChar >= policy.getLowerBound() && matchesWithPolicyChar <= policy.getUpperBound();
        if (valid) validPasswords++;
    }

}
