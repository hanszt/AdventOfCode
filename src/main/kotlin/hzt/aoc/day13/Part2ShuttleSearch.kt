package hzt.aoc.day13

import kotlin.math.abs

class Part2ShuttleSearch : Day13Challenge(
    "part 2",
    "What is the earliest timestamp such that all of the listed bus IDs depart at " +
            "offsets matching their positions in the list"
) {
    override fun solve(inputList: List<String>): String {
        val busIdsWithBlanks = sequenceOf(*inputList[1].split(",".toRegex()).toTypedArray())
            .map { if (it == "x") "-1" else it }
            .map(String::toInt)
            .toList()
        return second(busIdsWithBlanks).toString()
    }

    // Chinese remainder theorem implementation
    private fun second(busIdsWithBlanks: List<Int>): Long {
        val equations: MutableList<Equation> = ArrayList()
        for (i in busIdsWithBlanks.indices) {
            val busId = busIdsWithBlanks[i]
            if (busId != -1) {
                equations.add(Equation((-i).toLong(), busId.toLong()))
            }
        }
        val moduloProduct = equations
            .map(Equation::modulo)
            .reduce { a, b -> a * b }

        for (equation in equations) {
            equation.n = moduloProduct / equation.modulo
            equation.w = euclideanGeneralTheorem(equation.n, equation.modulo)
            if (equation.w < 0) equation.w += equation.modulo
        }
        val solution = equations.asSequence()
            .map { it.minIndex * it.n * it.w }
            .sum()
        return properModulo(solution, moduloProduct)
    }

    private fun euclideanGeneralTheorem(a1: Long, b1: Long): Long {
        var b = a1
        var a = b1
        var x: Long = 0
        var y: Long = 1
        var u: Long = 1
        var v: Long = 0
        while (b != 0L) {
            val q = a / b
            val r = properModulo(a, b)
            val m = x - u * q
            val n = y - v * q
            a = b
            b = r
            x = u
            y = v
            u = m
            v = n
        }
        if (a != 1L) LOGGER.error("!$a")
        return x
    }

    private fun properModulo(a: Long, b: Long): Long {
        var bMut = b
        bMut = abs(bMut)
        return if (a < 0) bMut - -a % bMut else a % bMut
    }

    internal class Equation(var minIndex: Long, var modulo: Long) {
        var n: Long = 0
        var w: Long = 0
    }
}
