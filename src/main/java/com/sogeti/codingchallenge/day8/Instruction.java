package com.sogeti.codingchallenge.day8;

class Instruction {


    private static int next = 0;
    private final int nr;
    private final int argument;

    private boolean visited;
    private String descriptor;

    public Instruction(String descriptor, int argument) {
        this.nr = ++next;
        this.descriptor = descriptor;
        this.argument = argument;
    }

    public static void setNext(int next) {
        Instruction.next = next;
    }

    public int getNr() {
        return nr;
    }

    public int getArgument() {
        return argument;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "nr=" + nr +
                ", visited=" + visited +
                ", descriptor='" + descriptor + '\'' +
                ", argument=" + argument +
                '}';
    }
}