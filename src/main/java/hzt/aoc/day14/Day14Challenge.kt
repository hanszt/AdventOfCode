package hzt.aoc.day14

import hzt.aoc.Challenge

abstract class Day14Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201214-input-day14.txt") {
    override fun solve(inputList: List<String>): String {
        val programs: MutableList<Program> = ArrayList()
        var program: Program? = null
        for (line in inputList) {
            val array = line.split(" = ".toRegex()).toTypedArray()
            if (array[0] == "mask") {
                program = Program(array[1])
                programs.add(program)
            } else if (program != null) {
                val value = array[1].toInt()
                val memoryLocation = array[0].substring(4, array[0].length - 1).toInt()
                program.put(value, memoryLocation)
            }
        }
        return getMessage(count(programs))
    }

    abstract fun count(programs: List<Program>): Long
    abstract fun getMessage(value: Long): String
}
