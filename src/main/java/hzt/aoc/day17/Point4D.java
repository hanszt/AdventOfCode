package hzt.aoc.day17;

public class Point4D extends Point {

    private final int w;


    public Point4D(int x, int y, int z, int w) {
        super(x, y, z);
        this.w = w;
    }

    @Override
    public boolean valueEquals(Point o) {
        Point4D other = (Point4D) o;
        return this.getX() == other.getX() && this.getY() == other.getY() &&
                this.getZ() == other.getZ() && this.getW() == other.getW();
    }

    public int getW() {
        return w;
    }
}
