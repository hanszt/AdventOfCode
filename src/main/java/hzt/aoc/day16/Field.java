package hzt.aoc.day16;

import hzt.aoc.Pair;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private static int next = 0;
    private final int nr;
    private final String fieldName;
    private final List<Pair<Integer, Integer>> valueRanges = new ArrayList<>();

    public Field(final String fieldName) {
        this.nr = next++;
        this.fieldName = fieldName;
    }

    public void addRange(final Pair<Integer, Integer> range) {
        if (range != null) {
            valueRanges.add(range);
        }
    }

    public boolean containsValueInRanges(final Integer integer) {
        for (final Pair<Integer, Integer> p : valueRanges) {
            if (integer >= p.getLeft() && integer <= p.getRight()) {
                return true;
            }
        }
        return false;
    }

    public int getNr() {
        return nr;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static void setNext(final int next) {
        Field.next = next;
    }

    @Override
    public String toString() {
        return "Field{" +
                "nr=" + nr +
                ", fieldName='" + fieldName + '\'' +
                ", valueRanges=" + valueRanges +
                '}';
    }
}
