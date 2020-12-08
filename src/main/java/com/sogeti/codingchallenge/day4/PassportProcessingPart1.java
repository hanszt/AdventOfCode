package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;

import java.util.List;

public class PassportProcessingPart1 extends Day4Challenge {

    public PassportProcessingPart1() {
        super("Passport Processing part 1", "Find the number of valid passports. ");
    }

    @Override
    protected long calculateResult(List<Passport> passports) {
        return passports.stream().filter(Passport::requiredFieldsPresent).count();
    }

}
