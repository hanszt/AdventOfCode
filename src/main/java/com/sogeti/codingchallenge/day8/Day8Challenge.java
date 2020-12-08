package com.sogeti.codingchallenge.day8;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Day8Challenge extends Challenge {

    static final String ACCUMULATOR = "acc";
    static final String JUMP = "jmp";
    static final String NO_OPERATION = "nop";
    protected Day8Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201208-input-day8.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        Instruction.setNext(0);
        List<Instruction> instructions = inputList.stream().map(this::instruction).collect(Collectors.toList());
        int global = solveByInstructions(instructions);
        return getMessage(global);
    }

    protected abstract int solveByInstructions(List<Instruction> instructions);

    Result testInstructions(List<Instruction> instructions) {
        int position = 0;
        int global = 0;
        Instruction lastInstruction = null;
        while (position < instructions.size() && !instructions.get(position).visited) {
            Instruction instruction = instructions.get(position);
            switch (instruction.descriptor) {
                case JUMP:
                    position += instruction.argument;
                    break;
                case ACCUMULATOR:
                    global += instruction.argument;
                    position++;
                    break;
                case NO_OPERATION:
                    position++;
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            instruction.visited = true;
            lastInstruction = instruction;
        }
        return new Result(lastInstruction, global);
    }

    private Instruction instruction(String string) {
        String[] strings = string.split("\\s");
        String descriptor = strings[0];
        String stringArgument = strings[1];
        int argument = Integer.parseInt(stringArgument);
        return new Instruction(descriptor, argument);
    }

    public abstract String getMessage(int global);

    static class Result {

        final Instruction lastInstruction;
        final int global;

        public Result(Instruction lastInstruction, int global) {
            this.lastInstruction = lastInstruction;
            this.global = global;
        }
    }

}
