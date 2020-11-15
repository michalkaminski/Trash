package odes.odes;

import javafx.util.Pair;
import odes.components.functions.IFunction;
import odes.components.functions.initialcondition.InitialConditions;
import odes.components.visualizations.EulerVisualizationVF2D;

public class Ode1VF {
    public static void main(String[] args) {
        double SCALE = 200;
        double STEP = 0.01d;
        double START = 0d;
        Pair<Double, Double>[] initialConditions = InitialConditions.getInitialConditions(
                -100, 100, 500);

        EulerVisualizationVF2D vis = new EulerVisualizationVF2D(
                new XFunction(),
                new YFunction(),
                initialConditions,
                SCALE,
                STEP,
                START
        );
        vis.visualize();
    }

    private static class XFunction implements IFunction {
        @Override
        public double valueOf(double... arg) {
            double x = arg[0];
            return x;
        }
        @Override
        public double derivative(double... arg) {
            double x = arg[0];
            double y=arg[1];
            return 0.5*y;
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
            return 0.5*x;
        }

        @Override
        public double secondDerivative(double... arg) {
            return 0;
        }
    }
}

