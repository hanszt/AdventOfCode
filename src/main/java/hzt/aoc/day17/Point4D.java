package hzt.aoc.day17;

import java.util.Objects;

public class Point4D extends Point {

    private final int w;


    public Point4D(int x, int y, int z, int w) {
        super(x, y, z);
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point4D)) return false;
        if (!super.equals(o)) return false;
        Point4D point4D = (Point4D) o;
        return w == point4D.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), w);
    }

    public int getW() {
        return w;
    }
}
