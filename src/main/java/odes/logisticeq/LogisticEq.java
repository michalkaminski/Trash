package odes.logisticeq;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import odes.components.functions.IFunction;
import odes.demos.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

/*
 *
 * Visualising what does it mean population growth curve. Iterating over logistic map to
 * increase/decrese population over period/time/iteration t. Around r=3.5 produces periodic
 * solution of population over the periods. For r around 3.9-4.0 produces chaotic prediction of
 * population size. Above 4.0 population extincts.
 *
 * X axis - population last year
 * Y axis - population this year
 *
 * */

@Slf4j
public class LogisticEq {

    private static double[] x = DoubleStream.iterate(-0.01, i -> i + 0.0005).limit(2100).toArray();
    private static double SCALE = Arrays.stream(x).map(v -> Math.abs(v)).max().getAsDouble();

    private static double STEP = 0.0001d;
    private static double START = 0d;

    /* > */
    private static int noOfInterations = 2000;
    private static double r = 3.50;
    private static double initialCondition = 0.001;
    /* < */

    public static void main(String[] args) {
        IFunction function = new LogisticFunction();


        LogisticEqVisualisation vis = new LogisticEqVisualisation(
                function,
                x,
                SCALE,
                STEP,
                START,

                initialCondition,
                noOfInterations
        );
        vis.visualize();
    }


    public static class LogisticFunction implements IFunction {

        // f(x) = rx(1-x)
        @Override
        public double valueOf(double... arg) {
            double x = arg[0];
            return r * x * (1 - x);
        }

        @Override
        public double derivative(double... arg) {
            // f(x) = rx(1-x)
            // f'(x) = (rx - rx^2)' = r - 2rx
            double x = arg[0];
            return r - 2 * r * x;
        }

        @Override
        public double secondDerivative(double... arg) {
            /* to implement */
            return 0;
        }
    }


}

class LogisticEqVisualisation {

    private IFunction function;
    private double x[];
    private double SCALE;
    private double STEP_SIZE;
    private double START;

    private double initialCondition;
    private int noOfIterations;

    private double defaultRadius = StdDraw.getPenRadius();


    LogisticEqVisualisation(
            IFunction function,
            double x[],
            double SCALE,
            double STEP,
            double START,

            double initialCondition,
            int noOfIterations
    ) {
        this.function = function;
        this.x = x;
        this.SCALE = SCALE;
        this.STEP_SIZE = STEP;
        this.START = START;

        this.initialCondition = initialCondition;
        this.noOfIterations = noOfIterations;
    }

    public void visualize() {

        StdDraw.setCanvasSize(1200, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();

        StdDraw.setXscale(-SCALE / 12, SCALE);
        StdDraw.setYscale(-SCALE / 8, SCALE);

        /** axis */
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.line(0, -SCALE, 0, SCALE);
        StdDraw.line(-SCALE, 0, SCALE, 0);

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        for (double xl = 0; xl < 1; xl = xl + 0.1)
        {
            StdDraw.line(xl, -0, xl, 1);
            StdDraw.line(-0, xl, 1, xl);

        }



        for (int i = 0; i <= x.length - 1; i++) {
            double currentValue = function.valueOf(this.x[i]);
            StdDraw.setPenRadius(0.001d);
            StdDraw.setPenColor(StdDraw.RED);
            if (!Double.isNaN(currentValue) && Double.isFinite(currentValue)) {
                StdDraw.point(x[i], currentValue);
            }
        }

        StdDraw.setPenColor(Color.DARK_GRAY);

        double populationLastYear = initialCondition;
        double populationThisYear;

        List<Pair<Double, Double>> doubleList = new ArrayList<>();


        for (int iteration = 0; iteration < noOfIterations - 1; iteration++) {

            populationThisYear = function.valueOf(populationLastYear);
            StdDraw.point(populationLastYear, populationThisYear);
            doubleList.add(new Pair<>(populationLastYear, populationThisYear));

            populationLastYear = populationThisYear;
        }


        for (int l = 0; l < doubleList.size() - 1; l = l + 1) {
            StdDraw.line(
                    doubleList.get(l).getKey(),
                    doubleList.get(l).getValue(),
                    doubleList.get(l + 1).getKey(),
                    doubleList.get(l + 1).getValue()
            );
        }

        StdDraw.setPenRadius(defaultRadius);
        StdDraw.show();
    }
}
