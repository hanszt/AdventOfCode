package com.sogeti.codingchallenge.day2;

import com.sogeti.codingchallenge.day2.model.Policy;

public class PasswordValidChallenge2 extends Day2Challenge {

    /**
     * The shopkeeper suddenly realizes that he just accidentally explained the password policy rules
     * from his old job at the sled rental place down the street! The Official Toboggan Corporate Policy actually works a little differently.
     * <p>
     * Each policy actually describes two positions in the password, where 1 means the first character,
     * 2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
     * Exactly one of these positions must contain the given letter. Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
     * <p>
     * Given the same example list from above:
     * <p>
     * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
     * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
     * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
     * <p>
     * How many passwords are valid according to the new interpretation of the policies?
     */

    public PasswordValidChallenge2() {
        super("Valid passwords part 2", "Count the passwords that are valid, see part two of ChallengeDay2.md for the validity rules");
    }

    @Override
    boolean isValid(String password, Policy policy) {
        int matchesWithPolicyChar = 0;
        if (password.charAt(policy.getLowerBound() - 1) == policy.getCharacter()) matchesWithPolicyChar++;
        if (password.charAt(policy.getUpperBound() - 1) == policy.getCharacter()) matchesWithPolicyChar++;
        return matchesWithPolicyChar == 1;
    }

}
