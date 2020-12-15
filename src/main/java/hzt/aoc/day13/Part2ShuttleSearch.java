package hzt.aoc.day13;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2ShuttleSearch extends Day13Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Part2ShuttleSearch.class);

    public Part2ShuttleSearch() {
        super("part 2",
                "What is the earliest timestamp such that all of the listed bus IDs depart at " +
                        "offsets matching their positions in the list?");
    }

    @Override
    protected Object solve(List<String> lines) {
        List<Integer> busIdsWithBlanks = Arrays.stream(lines.get(1).split(","))
                .map(x -> "x".equals(x) ? "-1" : x)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return getMessage(second(busIdsWithBlanks));
    }

    // Chinese remainder theorem implementation
    private long second(List<Integer> busIdsWithBlanks) {
        List<Equation> equations = new ArrayList<>();
        for (int i = 0; i < busIdsWithBlanks.size(); i++) {
            int busId = busIdsWithBlanks.get(i);
            if (busId != -1) {
                equations.add(new Equation(-i, busId));
            }
        }

        long moduloProduct = equations
                .stream()
                .map(equation -> equation.modulo)
                .reduce(1L, (a, b) -> a * b);

        for (Equation equation : equations) {
            equation.n = moduloProduct / equation.modulo;
            equation.w = euclideanGeneralTheorem(equation.n, equation.modulo);
            if (equation.w < 0) equation.w += equation.modulo;
        }

        long solution = equations
                .stream()
                .map(equation -> equation.minIndex * equation.n * equation.w)
                .reduce(0L, Long::sum);
        solution = properModulo(solution, moduloProduct);
        return solution;
    }

    private long euclideanGeneralTheorem(long a1, long b1) {
        long b = a1;
        long a = b1;

        long x = 0;
        long y = 1;
        long u = 1;
        long v = 0;
        while (b != 0) {
            long q = a / b;
            long r = properModulo(a, b);
            long m = x - u * q;
            long n = y - v * q;
            a = b;
            b = r;
            x = u;
            y = v;
            u = m;
            v = n;
        }
        if (a != 1) LOGGER.error("!" + a);
        return x;
    }

    private long properModulo(long a, long b) {
        b = Math.abs(b);
        if (a < 0) {
            return b - ((-a) % b);
        } else {
            return a % b;
        }
    }

    static class Equation {

        long minIndex;
        long modulo;
        long n;
        long w;

        public Equation(long minIndex, long modulo) {
            this.minIndex = minIndex;
            this.modulo = modulo;
        }
    }

    @Override
    String getMessage(Number global) {
        return String.format("%s", global);
    }
}
