package hzt.aoc.day13

import java.util.*
import java.util.stream.Collectors

class Part2ShuttleSearch : Day13Challenge(
    "part 2",
    "What is the earliest timestamp such that all of the listed bus IDs depart at " +
            "offsets matching their positions in the list"
) {
    override fun solve(inputList: List<String>): String {
        val busIdsWithBlanks = Arrays.stream(inputList[1].split(",".toRegex()).toTypedArray())
            .map { x: String -> if ("x" == x) "-1" else x }
            .map { s: String -> s.toInt() }
            .collect(Collectors.toList())
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
            .stream()
            .map { equation: Equation -> equation.modulo }
            .reduce(1L) { a: Long, b: Long -> a * b }
        for (equation in equations) {
            equation.n = moduloProduct / equation.modulo
            equation.w = euclideanGeneralTheorem(equation.n, equation.modulo)
            if (equation.w < 0) equation.w += equation.modulo
        }
        var solution = equations
            .stream()
            .map { equation: Equation -> equation.minIndex * equation.n * equation.w }
            .reduce(0L) { a: Long, b: Long -> java.lang.Long.sum(a, b) }
        solution = properModulo(solution, moduloProduct)
        return solution
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
        var b = b
        b = Math.abs(b)
        return if (a < 0) {
            b - -a % b
        } else {
            a % b
        }
    }

    internal class Equation(var minIndex: Long, var modulo: Long) {
        var n: Long = 0
        var w: Long = 0
    }
}
