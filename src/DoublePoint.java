public class DoublePoint {
    private double x, y;

    public DoublePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getXInt() {
        return (int)x;
    }

    public int getYInt() {
        return (int)y;
    }
}
