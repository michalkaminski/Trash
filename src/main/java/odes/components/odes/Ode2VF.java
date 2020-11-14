package odes.components.odes;

import javafx.util.Pair;
import odes.components.functions.IFunction;
import odes.components.functions.initialcondition.InitialConditions;
import odes.components.visualizations.EulerVisualizationVF2D;
import odes.components.visualizations.ScatterVisualization2D;
import org.jzy3d.analysis.AnalysisLauncher;

public class Ode2VF {
    public static void main(String[] args) throws Exception {
        double SCALE = 100;
        double STEP = 0.01d;
        double START = 0d;
        Pair<Double, Double>[] initialConditions = InitialConditions.getInitialConditions(
                -200, 200, 300);

        AnalysisLauncher.open(new ScatterVisualization2D(
                new XFunction(),
                new YFunction(),
                initialConditions,
                2,
                STEP,
                START
        ));

//        EulerVisualizationVF2D vis = new EulerVisualizationVF2D(
//                new XFunction(),
//                new YFunction(),
//                initialConditions,
//                SCALE,
//                STEP,
//                START
//        );
//        vis.visualize();
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
            double y = arg[1];
            return y;
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
            return -x - 0.25 * y;
        }

        @Override
        public double secondDerivative(double... arg) {
            return 0;
        }
    }
}

