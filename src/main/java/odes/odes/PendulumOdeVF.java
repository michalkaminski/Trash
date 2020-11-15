package odes.odes;

import javafx.util.Pair;
import odes.components.functions.IFunction;
import odes.components.functions.initialcondition.InitialConditions;
import odes.components.visualizations.EulerVisualizationVF2D;
import odes.components.visualizations.ScatterVisualization2D;
import org.jzy3d.analysis.AnalysisLauncher;

public class PendulumOdeVF {
    public static void main(String[] args) throws Exception {
        double SCALE = 300;
        double STEP = 0.05d;
        double START = 0d;
        double BOUNDARY=5d;
        Pair<Double, Double>[] initialConditions = InitialConditions.getInitialConditions(
                -BOUNDARY, BOUNDARY, 500);

        EulerVisualizationVF2D vis = new EulerVisualizationVF2D(
                new XFunction(),
                new YFunction(),
                initialConditions,
                20,
                14,
                STEP,
                START
        );
        vis.visualize();

        AnalysisLauncher.open(new ScatterVisualization2D(
                new XFunction(),
                new YFunction(),
                initialConditions,
                5,
                STEP,
                START
        ));


    }
//http://terpconnect.umd.edu/~petersd/246/matlabode2.html
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
            return -Math.sin(x);
        }

        @Override
        public double secondDerivative(double... arg) {
            return 0;
        }
    }
}

