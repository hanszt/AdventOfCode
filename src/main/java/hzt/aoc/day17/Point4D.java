package hzt.aoc.day17;

import java.util.Objects;

public class Point4D extends Point3D {

    private final int w;


    public Point4D(final int x, final int y, final int z, final int w) {
        super(x, y, z);
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Point4D point4D = (Point4D) o;
        return getW() == point4D.getW();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), w);
    }

    public int getW() {
        return w;
    }
}
