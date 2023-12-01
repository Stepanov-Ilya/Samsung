abstract public class GeometryObject implements Comparable<GeometryObject> {
    double square;

    abstract double getSquare();

    abstract void setScale(int scale);

    @Override
    public int compareTo(GeometryObject o) {
        if (this.square > o.square) {
            return 1;
        } else if (this.square < o.square) {
            return -1;
        }

        return 0;
    }

}
