import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        GeometryObject[] figures = new GeometryObject[]{new Triangle(3, 4, 5), new Circle(5), new Rectangle(2, 2)};
        Arrays.sort(figures);
        System.out.println(Arrays.toString(figures));
        figures[0].setScale(3);

        Arrays.sort(figures);

        System.out.println(Arrays.toString(figures));
    }
}