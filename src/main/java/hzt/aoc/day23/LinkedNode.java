package hzt.aoc.day23;

public class LinkedNode<T> {

    private T value;
    private LinkedNode<T> next;

    public LinkedNode() {
        this(null);
    }

    public LinkedNode(T value) {
        this.setValue(value);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "CustomLinkedNode{" + getValue() + '}';
    }
}
