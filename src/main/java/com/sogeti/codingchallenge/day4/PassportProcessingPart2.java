package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;

import java.util.List;

public class PassportProcessingPart2 extends Day4Challenge {

    public PassportProcessingPart2() {
        super("Counting valid passports day 4 part 2", "");
    }

    @Override
    protected void calculateResult(List<Passport> passports) {
        for (Passport p : passports) {
            if (p.fieldsMeetCriteria()) validPassports++;
            totalPassportsChecked++;
        }
    }

}
