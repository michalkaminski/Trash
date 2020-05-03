package complexsystems.visualisation;

import lombok.extern.slf4j.Slf4j;
import odes.demos.StdDraw;

import java.awt.*;
import java.util.List;

@Slf4j
public class Visualization2D {

    private static final double AXIS_PEN_SIZE = 0.001d;
    double SCALE;
    double PEN_SIZE;
    boolean SHOW_AXES = true;

    public void setSHOW_AXES(boolean SHOW_AXES) {
        this.SHOW_AXES = SHOW_AXES;
    }

    public Visualization2D(
            double PEN_SIZE,
            double SCALE,
            int WIDTH,
            int HEIGHT
    ) {
        this.SCALE = SCALE;
        this.PEN_SIZE = PEN_SIZE;
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(-SCALE, SCALE);
        StdDraw.setYscale(-SCALE, SCALE);
        StdDraw.enableDoubleBuffering();

    }


    public void visualize(List<Point> valuesToVisualize) {
        StdDraw.clear(Color.black);
        if (SHOW_AXES) axis();
        StdDraw.setPenRadius(PEN_SIZE);
        valuesToVisualize.parallelStream()
                .forEach(point -> {
                    if (checkPair(point)) {
                        StdDraw.setPenColor(ColorPercentRange.getColor((float)point.getColor()));
                        StdDraw.point(point.getX(), point.getY());
                    }
                });
        StdDraw.show();
    }

    private void axis() {
        /** axis */
        StdDraw.setPenRadius(AXIS_PEN_SIZE);

        StdDraw.setPenColor(new Color(103, 198, 243, 80));
        StdDraw.line(0, -SCALE, 0, SCALE); //y
        StdDraw.line(-SCALE, 0, SCALE, 0); //x
    }

    private boolean checkPair(Point point) {
        Double x = point.getX();
        Double y = point.getY();
        return (!Double.isNaN(x) && Double.isFinite(x) && !Double.isNaN(y) && Double.isFinite(y));
    }

}
