package odes.odes;

import lombok.extern.slf4j.Slf4j;
import odes.components.functions.IFunction;
import odes.components.functions.initialcondition.InitialConditions;
import odes.demos.StdDraw;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.awt.*;

/*
 * visualization of Lorenz equations in 2D
 *
 * */

@Slf4j
public class LorenzEq2D {

    static double STEP = 0.001d;
    static double START = 0d;
    private static double BOUNDARY = 0.01d;
    static ImmutableTriple<Double, Double, Double>[] initialConditions = InitialConditions.getInitialConditions3D(
            -BOUNDARY, BOUNDARY, 2);

    public static void main(String[] args) {

        double SCALE = 50;
        LogisticEqVisualisation vis = new LogisticEqVisualisation(
                new XFunction(),
                new YFunction(),
                new ZFunction(),
                initialConditions,
                SCALE,
                STEP,
                START
        );
        vis.visualize();
    }


    //https://www.pacifict.com/WhatsNew.html
    private static class XFunction implements IFunction {
        @Override
        public double valueOf(double... arg) {
            double x = arg[0];
            double y = arg[1];
            return y;
        }

        @Override
        public double derivative(double... arg) {
            double x = arg[0];
            double y = arg[1];
            double z = arg[2];
            return 10 * (y - x);
        }

        @Override
        public double secondDerivative(double... arg) {
            return 0;
        }
    }

    private static class YFunction implements IFunction {
        @Override
        public double valueOf(double... arg) {
            double x = arg[0];
            return x;
        }

        @Override
        public double derivative(double... arg) {
            double x = arg[0];
            double y = arg[1];
            double z = arg[2];
            return x * (28 - z) - y;
        }

        @Override
        public double secondDerivative(double... arg) {
            return 0;
        }
    }

    private static class ZFunction implements IFunction {
        @Override
        public double valueOf(double... arg) {
            double x = arg[0];
            return x;
        }

        @Override
        public double derivative(double... arg) {
            double x = arg[0];
            double y = arg[1];
            double z = arg[2];
            return x * y - (8 / 3) * z;
        }

        @Override
        public double secondDerivative(double... arg) {
            return 0;
        }
    }

}


class LogisticEqVisualisation {

    private double defaultRadius = StdDraw.getPenRadius();

    private IFunction functionX;
    private IFunction functionY;
    private IFunction functionZ;
    private ImmutableTriple<Double, Double, Double>[] initialConditions;
    private double SCALE;
    private double START;
    private double h;

    LogisticEqVisualisation(
            IFunction functionX,
            IFunction functionY,
            IFunction functionZ,
            ImmutableTriple<Double, Double, Double>[] initialConditions,
            double SCALE,
            double STEP,
            double START
    ) {
        this.functionX = functionX;
        this.functionY = functionY;
        this.functionZ = functionZ;
        this.initialConditions = initialConditions;
        this.SCALE = SCALE;
        this.h = STEP;
        this.START = START;

    }

    public void visualize() {

        StdDraw.setCanvasSize(1200, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        StdDraw.setXscale(-SCALE / 12, SCALE);
        StdDraw.setYscale(-SCALE / 2, SCALE);

        /** axis */
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.line(0, -SCALE, 0, SCALE);
        StdDraw.line(-SCALE, 0, SCALE, 0);

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        for (double xl = 0; xl < SCALE; xl = xl + 1) {
            StdDraw.line(xl, -SCALE, xl, SCALE);
            StdDraw.line(-SCALE, xl, SCALE, xl);
        }


        StdDraw.setPenRadius(0.001d);

        double sX;
        double sY;
        double sZ;

        for (int i = 0; i <= initialConditions.length - 1; i++) {

            float x = this.initialConditions[i].getLeft().floatValue();
            float y = this.initialConditions[i].getMiddle().floatValue();
            float z = this.initialConditions[i].getRight().floatValue();

            for (double t = -START; t <= SCALE; t += h) {

                sX = h * functionX.derivative(x, y, z);
                sY = h * functionY.derivative(x, y, z);
                sZ = h * functionZ.derivative(x, y, z);

                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.point(t, x);

                StdDraw.setPenColor(StdDraw.ORANGE);
                StdDraw.point(t, y);

                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.point(t, z);

                x += sX;
                y += sY;
                z += sZ;
            }
        }

        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.setPenRadius(defaultRadius);
        StdDraw.show();
    }

    private boolean isOkValue(double currentValue) {
        if (!Double.isNaN(currentValue) && Double.isFinite(currentValue)) {
            return true;
        }
        return false;
    }
}
