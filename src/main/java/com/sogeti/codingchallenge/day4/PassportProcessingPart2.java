package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;

import java.util.List;

public class PassportProcessingPart2 extends Day4Challenge {

    public PassportProcessingPart2() {
        super("Passport Processing part 2", "Find the number of valid passports. ");
    }

    @Override
    protected void calculateResult(List<Passport> passports) {
        for (Passport p : passports) {
            if (p.fieldsMeetCriteria()) validPassports++;
            totalPassportsChecked++;
        }
    }

}
