package hzt.aoc.day08

import hzt.aoc.Challenge
import hzt.aoc.day08.Instruction.Companion.setNext
import java.util.stream.Collectors

abstract class Day08Challenge protected constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201208-input-day8.txt") {

    override fun solve(inputList: List<String>): String {
        setNext(0)
        val instructions = inputList.stream().map { string: String -> instruction(string) }
            .collect(Collectors.toList())
        val global = solveByInstructions(instructions)
        return global.toString()
    }

    protected abstract fun solveByInstructions(instructions: List<Instruction>): Int
    fun testInstructions(instructions: List<Instruction>): Result {
        var position = 0
        var global = 0
        var lastInstruction: Instruction? = null
        while (position < instructions.size && !instructions[position].isVisited) {
            val instruction = instructions[position]
            when (instruction.descriptor) {
                JUMP -> position += instruction.argument
                ACCUMULATOR -> {
                    global += instruction.argument
                    position++
                }
                NO_OPERATION -> position++
                else -> throw UnsupportedOperationException()
            }
            instruction.isVisited = true
            lastInstruction = instruction
        }
        return Result(lastInstruction, global)
    }

    private fun instruction(string: String): Instruction {
        val strings = string.split("\\s".toRegex()).toTypedArray()
        val descriptor = strings[0]
        val stringArgument = strings[1]
        val argument = stringArgument.toInt()
        return Instruction(descriptor, argument)
    }

    class Result(val lastInstruction: Instruction?, val global: Int)
    companion object {
        const val ACCUMULATOR = "acc"
        const val JUMP = "jmp"
        const val NO_OPERATION = "nop"
    }
}
