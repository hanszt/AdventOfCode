package hzt.aoc.day16

import hzt.aoc.Pair

class Field(fieldName: String) {

    private val nr: Int = next++
    private val fieldName: String
    private val valueRanges: MutableList<Pair<Int, Int>> = ArrayList()

    fun addRange(range: Pair<Int, Int>) {
        valueRanges.add(range)
    }

    fun containsValueInRanges(integer: Int): Boolean {
        for (p in valueRanges) {
            if (integer >= p.left && integer <= p.right) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        return "Field{" +
                "nr=" + nr +
                ", fieldName='" + fieldName + '\'' +
                ", valueRanges=" + valueRanges +
                '}'
    }

    companion object {
        private var next = 0
        fun setNext(next: Int) {
            Companion.next = next
        }
    }

    init {
        this.fieldName = fieldName
    }
}
