package hzt.aoc.day17

import java.util.*

open class Point(val x: Int, val y: Int, val z: Int) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Point) return false
        return x == other.x && y == other.y && z == other.z
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y, z)
    }
}
