public class Rectangle extends GeometryObject {
    protected int a, b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
        square = getSquare();
    }

    @Override
    double getSquare() {
        return a * b;
    }

    @Override
    void setScale(int scale) {
        a *= scale;
        b *= scale;
        square = getSquare();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                ", square=" + square +
                '}';
    }
}
