package libra.odes.odes;

import javafx.util.Pair;
import libra.functions.IFunction;
import libra.initialcondition.InitialConditions;
import libra.odes.visual.ScatterVisualization2D;
import libra.odes.visual.ScatterVisualization3D;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.jzy3d.analysis.AnalysisLauncher;

public class D3OdeVF {
    public static void main(String[] args) throws Exception {
        double STEP = 0.01d;
        double START = 0d;
        double BOUNDARY=0.001d;
        ImmutableTriple<Double, Double, Double>[] initialConditions = InitialConditions.getInitialConditions3D(
                -BOUNDARY, BOUNDARY, 5000);

        AnalysisLauncher.open(new ScatterVisualization3D(
                new XFunction(),
                new YFunction(),
                new ZFunction(),
                initialConditions,
                2,
                STEP,
                START
        ));


    }
//https://www.geogebra.org/m/KKB2Ndez
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
            return x/(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
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
            return y/(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
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
            return z/(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));

        }
    }

}

