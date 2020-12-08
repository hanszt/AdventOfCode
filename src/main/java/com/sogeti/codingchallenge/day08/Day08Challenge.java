package com.sogeti.codingchallenge.day08;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class Day08Challenge extends Challenge {

    static final String ACCUMULATOR = "acc";
    static final String JUMP = "jmp";
    static final String NO_OPERATION = "nop";
    protected Day08Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201208-input-day8.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        Instruction.setNext(0);
        List<Instruction> instructions = inputList.stream().map(this::instruction).collect(toList());
        int global = solveByInstructions(instructions);
        return getMessage(global);
    }

    protected abstract int solveByInstructions(List<Instruction> instructions);

    Result testInstructions(List<Instruction> instructions) {
        int position = 0;
        int global = 0;
        Instruction lastInstruction = null;
        while (position < instructions.size() && !instructions.get(position).isVisited()) {
            Instruction instruction = instructions.get(position);
            switch (instruction.getDescriptor()) {
                case JUMP:
                    position += instruction.getArgument();
                    break;
                case ACCUMULATOR:
                    global += instruction.getArgument();
                    position++;
                    break;
                case NO_OPERATION:
                    position++;
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            instruction.setVisited(true);
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

        private final Instruction lastInstruction;
        private final int global;

        public Result(Instruction lastInstruction, int global) {
            this.lastInstruction = lastInstruction;
            this.global = global;
        }

        public Instruction getLastInstruction() {
            return lastInstruction;
        }

        public int getGlobal() {
            return global;
        }
    }

}
