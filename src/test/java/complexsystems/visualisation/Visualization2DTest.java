package complexsystems.visualisation;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Visualization2DTest {

    static List<Point> values = Arrays.asList(
            new Point(0d, 1d, 1),
            new Point(1d, 2d, 1)
    );

    static Visualization2D visualization2D = new Visualization2D(
            0.01d,
            100,
            1500,
            1500);


    @Test
    public void visualize() {
    }


    public static void main(String[] args) {
        visualization2D.visualize(values);

    }
}