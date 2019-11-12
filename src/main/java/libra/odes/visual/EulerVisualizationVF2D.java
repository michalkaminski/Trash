package libra.odes.visual;

import javafx.util.Pair;
import libra.functions.IFunction;
import lombok.extern.slf4j.Slf4j;
import n.draw.StdDraw;

import java.awt.*;


@Slf4j
public class EulerVisualizationVF2D {

    IFunction functionX;
    IFunction functionY;

    Pair<Double, Double> initialCondition[];

    double SCALE;
    double h;
    double START;
    double MAX=0;

    double defaultRadius = StdDraw.getPenRadius();

    public EulerVisualizationVF2D(
            IFunction functionX,
            IFunction functionY,
            Pair<Double, Double> initialCondition[],
            double SCALE,
            double STEP,
            double START
    ) {
        this.functionX = functionX;
        this.functionY=functionY;
        this.initialCondition = initialCondition;
        this.SCALE = SCALE;
        this.h = STEP;
        this.START = START;
    }


    public EulerVisualizationVF2D(
            IFunction functionX,
            IFunction functionY,
            Pair<Double, Double> initialCondition[],
            double SCALE,
            double MAX,
            double STEP,
            double START
    ) {
        this.functionX = functionX;
        this.functionY=functionY;
        this.initialCondition = initialCondition;
        this.SCALE = SCALE;
        this.MAX=MAX;
        this.h = STEP;
        this.START = START;
    }

    public void visualize() {

        setUpGraph();

        double sX;
        double sY;

        for (int i = 0; i <= initialCondition.length-1; i++) {
            double x = this.initialCondition[i].getKey();
            double y = this.initialCondition[i].getValue();

            for (double t = -START; t <= SCALE; t += h) {

                StdDraw.setPenRadius(0.001d);


                sX = h*functionX.derivative(x,y);
                sY = h*functionY.derivative(x,y);

                int color = new Double(1905000/h*(Math.pow(Math.pow(sX, 2) + Math.pow(sY, 2), 0.5))).intValue();
               // log.info(""+1000/h*(Math.pow(Math.pow(sX, 2) + Math.pow(sY, 2), 0.5)));
                StdDraw.point(x, y);

                StdDraw.setPenColor(new Color(color));
                StdDraw.line(x,y, x+sX, y+sY);

                x+=sX;
                y+=sY;

            }
        }
        finalizeGraph();
    }


    private void finalizeGraph() {
        StdDraw.setPenRadius(defaultRadius);
        StdDraw.show();
    }


    private void setUpGraph() {
        StdDraw.setCanvasSize(1200, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        if(MAX==0) {
            StdDraw.setXscale(-SCALE, SCALE);
            StdDraw.setYscale(-SCALE, SCALE);
        }
        else
        {
            StdDraw.setXscale(-MAX, MAX);
            StdDraw.setYscale(-MAX, MAX);
        }
        /** axis */
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.line(0, -SCALE, 0, SCALE);
        StdDraw.line(-SCALE, 0, SCALE, 0);
    }
}
