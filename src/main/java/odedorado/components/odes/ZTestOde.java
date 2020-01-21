package odedorado.components.odes;

import javafx.util.Pair;
import odedorado.components.functions.IFunction;
import odedorado.components.functions.initialcondition.InitialConditions;
import odedorado.components.visualizations.EulerVisualizationVF2D;

public class ZTestOde {
    public static void main(String[] args) throws Exception {
        double SCALE = 50;
        double STEP = 0.01d;
        double START = 0d;
        Pair<Double, Double>[] initialConditions = InitialConditions.getInitialConditions(
                -2, 2, 200);


        EulerVisualizationVF2D eVF2D=new EulerVisualizationVF2D(
                new XFunction(),
                new YFunction(),
                initialConditions,
                SCALE,
                2,
                STEP,
                START

        );
        eVF2D.visualize();

//        AnalysisLauncher.open(new ScatterVisualization(
//                new XFunction(),
//                new YFunction(),
//                initialConditions,
//                SCALE,
//                STEP,
//                START
//        ));

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

