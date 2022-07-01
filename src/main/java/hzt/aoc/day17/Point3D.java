package hzt.aoc.day17;

import java.util.Objects;

public class Point3D {

    private final int x;
    private final int y;
    private final int z;

    public Point3D(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point3D point3D = (Point3D) o;
        return getX() == point3D.getX() && getY() == point3D.getY() && getZ() == point3D.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
