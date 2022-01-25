package hzt.aoc.day16;

import hzt.aoc.Pair;
import hzt.collections.MutableListX;

public class Field {

    private static int next = 0;
    private final int nr;
    private final String fieldName;
    private final MutableListX<Pair<Integer, Integer>> valueRanges = MutableListX.empty();

    public Field(String fieldName) {
        this.nr = next++;
        this.fieldName = fieldName;
    }

    public void addRange(Pair<Integer, Integer> range) {
        if (range != null) {
            valueRanges.add(range);
        }
    }

    public boolean containsValueInRanges(Integer integer) {
        return valueRanges.any(p -> integer >= p.getLeft() && integer <= p.getRight());
    }

    public int getNr() {
        return nr;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static void setNext(int next) {
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
