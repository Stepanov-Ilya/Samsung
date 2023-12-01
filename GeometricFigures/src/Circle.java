public class Circle extends GeometryObject {
    double r;

    public Circle(double r) {
        this.r = r;
        square = getSquare();
    }

    @Override
    double getSquare() {
        return Math.PI * Math.pow(r, 2);

    }

    @Override
    void setScale(int scale) {
        r *= scale;
        square = getSquare();
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                ", square=" + square +
                '}';
    }
}
