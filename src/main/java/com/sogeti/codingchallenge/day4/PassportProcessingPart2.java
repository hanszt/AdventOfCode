package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;

import java.util.List;

public class PassportProcessingPart2 extends Day4Challenge {

    public PassportProcessingPart2() {
        super("Passport Processing part 2", "Find the number of valid passports. ");
    }

    @Override
    protected long calculateResult(List<Passport> passports) {
        return passports.stream().filter(Passport::fieldsMeetCriteria).count();
    }

}
