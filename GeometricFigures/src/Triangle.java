public class Triangle extends GeometryObject {
    protected int a, b, c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        square = getSquare();
    }

    @Override
    double getSquare() {
        double p = (double) ((a + b + c) / 2);
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));

    }

    @Override
    void setScale(int scale) {
        a *= scale;
        b *= scale;
        c *= scale;
        square = getSquare();
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", square=" + square +
                '}';
    }
}
