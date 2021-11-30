package hzt.aoc.day14

import hzt.aoc.Pair
import java.util.function.Consumer

class Part1DockingData : Day14Challenge(
    "Docking Data part 1",
    "Execute the initialization program. What is the sum of all values left in memory after it completes"
) {
    override fun count(programs: List<Program>): Long {
        val valuesInMemory: MutableMap<Int, Long> = HashMap()
        for (p in programs) {
            p.forEach(Consumer { e: Pair<Int, Int> ->
                valuesInMemory[e.right] = p.getValueStoredAfterBitMaskApplication(e.left)
            })
        }
        return valuesInMemory.values.stream().reduce { a: Long, b: Long -> java.lang.Long.sum(a, b) }.orElse(0L)
    }

    override fun getMessage(value: Long): String = String.format("%d", value)
}
