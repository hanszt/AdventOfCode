package hzt.aoc.day02.model

class Policy(val lowerBound: Int, val upperBound: Int, val character: Char) {
    override fun toString(): String {
        return "Policy{" +
                "lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                ", character=" + character +
                '}'
    }
}
