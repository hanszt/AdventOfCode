package hzt.aoc.day08

class Instruction(descriptor: String, argument: Int) {
    val nr: Int
    val argument: Int
    var isVisited = false
    var descriptor: String
    override fun toString(): String {
        return "Instruction{" +
                "nr=" + nr +
                ", visited=" + isVisited +
                ", descriptor='" + descriptor + '\'' +
                ", argument=" + argument +
                '}'
    }

    companion object {
        private var next = 0
        fun setNext(next: Int) {
            Companion.next = next
        }
    }

    init {
        nr = ++next
        this.descriptor = descriptor
        this.argument = argument
    }
}
