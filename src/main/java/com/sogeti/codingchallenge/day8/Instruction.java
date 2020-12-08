package com.sogeti.codingchallenge.day8;

class Instruction {


    private static int next = 0;
    final int nr;
    final int argument;
    int global;
    boolean visited;
    String descriptor;

    public Instruction(String descriptor, int argument) {
        this.nr = ++next;
        this.descriptor = descriptor;
        this.argument = argument;
    }

    public static void setNext(int next) {
        Instruction.next = next;
    }

    public int getGlobal() {
        return global;
    }

    public void setGlobal(int global) {
        this.global = global;
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