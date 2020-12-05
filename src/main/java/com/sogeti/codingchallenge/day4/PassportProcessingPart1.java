package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;

import java.util.List;

public class PassportProcessingPart1 extends Day4Challenge {

    public PassportProcessingPart1() {
        super("Passport Processing part 1", "Find the number of valid passports. ");
    }

    @Override
    protected void calculateResult(List<Passport> passports) {
        for (Passport p : passports) {
            if (p.requiredFieldsPresent()) validPassports++;
            totalPassportsChecked++;
        }
    }

}
