package hzt.aoc.day04

import hzt.aoc.day04.model.Passport

class PassportProcessingPart1 : Day04Challenge("part 1", "Find the number of valid passports. ") {
    override fun calculateResult(passports: List<Passport>): Long {
        return passports.stream().filter { obj: Passport -> obj.requiredFieldsPresent() }.count()
    }
}
