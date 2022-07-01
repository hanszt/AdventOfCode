package hzt.aoc;

import java.util.Objects;

public final class Point2D {

    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D subtract(Point2D other) {
        return subtract(other.getX(), other.getY());
    }

    public Point2D subtract(int x, int y) {
        return new Point2D(getX() - x, getY() - y);
    }

    public Point2D add(int x, int y) {
        return new Point2D(getX() + x, getY() + y);
    }

    public Point2D add(Point2D other) {
        return add(other.getX(), other.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point2D that = (Point2D) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
