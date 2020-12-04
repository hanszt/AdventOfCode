package com.sogeti.codingchallenge.day4;

import com.sogeti.codingchallenge.day4.model.Passport;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class PassportProcessingPart1 extends Day4Challenge {

    private static final Logger LOGGER = LogManager.getLogger(PassportProcessingPart1.class);

    private int result = 0;

    public PassportProcessingPart1() {
        super("Passport Processing part 1", "Find the number of valid passwords. ");
    }

    @Override
    protected void calculateResult(List<Passport> passports) {
        for (Passport p : passports) {
            if (p.requiredFieldsPresent()) result++;
            totalPassportsChecked++;
        }
    }

    @Override
    public void printResult() {
        LOGGER.info(String.format("The number of valid passwords is: %d of %d", result, totalPassportsChecked));
    }

}
