package hzt.aoc.day04

import hzt.aoc.day04.model.Passport

class PassportProcessingPart2 : Day04Challenge("part 2", "Find the number of valid passports. ") {

    override fun calculateResult(passports: List<Passport>): Long =
        passports.count(Passport::fieldsMeetCriteria).toLong()
}
