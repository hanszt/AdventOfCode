package hzt.aoc.day17

import java.util.*

class Point4D(x: Int, y: Int, z: Int, val w: Int) : Point(x, y, z) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Point4D) return false
        if (!super.equals(other)) return false
        return w == other.w
    }

    override fun hashCode(): Int {
        return Objects.hash(super.hashCode(), w)
    }
}
