package hzt.aoc.day23

class LinkedNode<T> @JvmOverloads constructor(value: T? = null) {
    var value: T? = null
    var next: LinkedNode<T>? = null
    override fun toString(): String {
        return "CustomLinkedNode{$value}"
    }

    init {
        this.value = value
    }
}
