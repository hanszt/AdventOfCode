package hzt.aoc.day04;

import hzt.aoc.day04.model.Passport;
import hzt.collections.ListX;

public class PassportProcessingPart2 extends Day04Challenge {

    public PassportProcessingPart2() {
        super("part 2", "Find the number of valid passports. ");
    }

    @Override
    protected long calculateResult(ListX<Passport> passports) {
        return passports.count(Passport::fieldsMeetCriteria);
    }

}
