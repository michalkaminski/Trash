package odedorado.components.visualizations;

import odedorado.components.functions.IFunction;
import lombok.extern.slf4j.Slf4j;
import odedorado.demos.StdDraw;


@Slf4j
public class EulerVisualization {

    IFunction function;
    double initialCondition[];
    double SCALE;
    double STEP_SIZE;
    double START;

    double defaultRadius = StdDraw.getPenRadius();

    public EulerVisualization(
            IFunction function,
            double initialCondition[],
            double SCALE,
            double STEP,
            double START
    ) {
        this.function = function;
        this.initialCondition = initialCondition;
        this.SCALE = SCALE;
        this.STEP_SIZE = STEP;
        this.START = START;
    }

    public void visualize() {

        StdDraw.setCanvasSize(1200, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        StdDraw.setXscale(-SCALE, SCALE);
        StdDraw.setYscale(-SCALE, SCALE);

        /** axis */
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.line(0, -SCALE, 0, SCALE);
        StdDraw.line(-SCALE, 0, SCALE, 0);


        for (int i = 0; i <= initialCondition.length-1; i++) {
            double currentValue = this.initialCondition[i];
            log.info("iteration {} value {}", i, currentValue);

            for (double t = -START; t <= SCALE; t += STEP_SIZE) {
                StdDraw.setPenRadius(0.001d);
                StdDraw.setPenColor(StdDraw.RED);
                if(!Double.isNaN(t) &&Double.isFinite(t) && !Double.isNaN(currentValue) &&Double.isFinite(currentValue)) {
                    StdDraw.point(t, currentValue);
                }
                currentValue += STEP_SIZE * function.derivative(currentValue);
            }
        }

        StdDraw.setPenRadius(defaultRadius);
        StdDraw.show();
    }
}
