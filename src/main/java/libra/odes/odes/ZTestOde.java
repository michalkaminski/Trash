package libra.odes.odes;

import javafx.util.Pair;
import libra.functions.IFunction;
import libra.initialcondition.InitialConditions;
import libra.odes.visual.EulerVisualizationVF2D;
import libra.odes.visual.ScatterVisualization;
import org.jzy3d.analysis.AnalysisLauncher;

public class ZTestOde {
    public static void main(String[] args) throws Exception {
        double SCALE = 2;
        double STEP = 0.003d;
        double START = 0d;
        Pair<Double, Double>[] initialConditions = InitialConditions.getInitialConditions(
                -2, 2, 200);


        AnalysisLauncher.open(new ScatterVisualization(
                new XFunction(),
                new YFunction(),
                initialConditions,
                SCALE,
                STEP,
                START
        ));

    }

    private static class XFunction implements IFunction {
        @Override
        public double valueOf(double... arg) {
            double x = arg[0];
            double y = arg[1];
            return x;
        }

        @Override
        public double derivative(double... arg) {
            double x = arg[0];
            double y = arg[1];
            return y/ Math.pow((Math.pow(x, 2) + Math.pow(y, 2)),0.5);
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
            return -x/ Math.pow((Math.pow(x, 2) + Math.pow(y, 2)),2);
        }
    }
}

