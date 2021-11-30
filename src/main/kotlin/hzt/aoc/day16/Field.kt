package hzt.aoc.day16

class Field(fieldName: String) {

    private val nr: Int = next++
    private val fieldName: String
    private val valueRanges: MutableList<Pair<Int, Int>> = ArrayList()

    fun addRange(range: Pair<Int, Int>) = valueRanges.add(range)

    fun containsValueInRanges(value: Int): Boolean = valueRanges.any { value >= it.first && value <= it.second}

    override fun toString(): String = "Field{" +
            "nr=" + nr +
            ", fieldName='" + fieldName + '\'' +
            ", valueRanges=" + valueRanges +
            '}'

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
