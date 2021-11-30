package hzt.aoc.day23

import java.util.HashMap

// Credits to TurkeyDev
class Part2CrabCups : Day23Challenge(
    "part 2",
    "You are quite surprised when the crab starts arranging many cups in a circle on your raft - one million (1000000) in total. " +
            "The crab is going to do ten million (10000000) moves! " +
            "Determine which two cups will end up immediately clockwise of cup 1. What do you get if you multiply their labels together"
) {
    override fun calculateAnswer(cups: MutableList<Int>): Long {
        val labelToNodeMap: MutableMap<Int, LinkedNode<Int>?> = HashMap()
        val first = firstNode(cups)
        var current: LinkedNode<Int>? = first
        var target: LinkedNode<Int>? = LinkedNode()
        var next: LinkedNode<Int>
        for (i in cups.indices) {
            val label = cups[i]
            if (i != 0) {
                next = LinkedNode(label)
                current?.next = next
                current = next
            }
            if (label == TARGET_VAL) target = current
            labelToNodeMap[label] = current
        }
        current = fillRestOfTheMap(labelToNodeMap, current)
        current?.next = first
        return run(first, target, labelToNodeMap)
    }

    private fun firstNode(cupLabels: List<Int>): LinkedNode<Int> {
        val label = cupLabels[0]
        return LinkedNode(label)
    }

    private fun fillRestOfTheMap(
        labelToNodeMap: MutableMap<Int, LinkedNode<Int>?>, current: LinkedNode<Int>?): LinkedNode<Int>? {
        var mutCurrent: LinkedNode<Int>? = current
        for (label in labelToNodeMap.size + 1..CUP_AMOUNT) {
            val next = LinkedNode(label)
            labelToNodeMap[label] = next
            mutCurrent?.next = next
            mutCurrent = next
        }
        return mutCurrent
    }

    private fun run(
        current: LinkedNode<Int>?,
        target: LinkedNode<Int>?,
        indexToLabelNodeMap: Map<Int, LinkedNode<Int>?>
    ): Long {
        var mutCurrent: LinkedNode<Int>? = current
        for (i in 0 until NR_OF_MOVES) {
            val moved = mutCurrent?.next
            mutCurrent?.next = mutCurrent?.next?.next?.next?.next
            val destinationCupLabel = determineTargetCupLabel(mutCurrent, moved, indexToLabelNodeMap)
            val nodeToBeInserted = indexToLabelNodeMap[destinationCupLabel]
            if (nodeToBeInserted != null) {
                moved?.next?.next?.next = nodeToBeInserted.next
                nodeToBeInserted.next = moved
            }
            mutCurrent = mutCurrent?.next
        }
        val num1: Long = target?.next?.value?.toLong() ?: 0
        val num2: Long = target?.next?.next?.value?.toLong() ?: 0
        return num1 * num2
    }

    private fun determineTargetCupLabel(
        current: LinkedNode<Int>?,
        removed: LinkedNode<Int>?,
        indexToLabelNodeMap: Map<Int, LinkedNode<Int>?>
    ): Int {
        var destinationCupLabel =
            if (current?.value == TARGET_VAL) indexToLabelNodeMap.size else current?.value ?: (- 1)
        while (inRange(removed, destinationCupLabel)) {
            destinationCupLabel--
            if (destinationCupLabel == 0) destinationCupLabel = indexToLabelNodeMap.size
        }
        return destinationCupLabel
    }

    private fun inRange(removed: LinkedNode<Int>?, destination: Int): Boolean {
        val current: Int? = removed?.value
        val next: Int? = removed?.next?.value
        val secondNext: Int? = removed?.next?.next?.value
        return current == destination || next == destination || secondNext == destination
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }

    companion object {
        private const val NR_OF_MOVES = 10000000
        private const val CUP_AMOUNT = 1000000
        private const val TARGET_VAL = 1
    }
}
