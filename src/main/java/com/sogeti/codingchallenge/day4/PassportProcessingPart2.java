package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class PassportProcessingPart2 extends Day4Challenge {


    private static final Logger LOGGER = LogManager.getLogger(PassportProcessingPart2.class);


    public PassportProcessingPart2() {
        super("Counting valid passports day 4 part 2", "");
    }

    private long result = 0;


    @Override
    protected void calculateResult(List<Passport> passports) {
        for (Passport p : passports) {
            if (p.fieldsMeetCriteria()) result++;
            totalPassportsChecked++;
        }
    }

    @Override
    public void printResult() {
        LOGGER.info(String.format("The number of valid passwords is: %d of %d", result, totalPassportsChecked));
    }

}
