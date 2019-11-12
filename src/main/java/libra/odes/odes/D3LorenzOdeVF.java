package libra.odes.odes;

import libra.functions.IFunction;
import libra.initialcondition.InitialConditions;
import libra.odes.visual.ScatterVisualization3D;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.jzy3d.analysis.AnalysisLauncher;

public class D3LorenzOdeVF {
    public static void main(String[] args) throws Exception {
        double STEP = 0.001d;
        double START = 0d;
        double BOUNDARY=20.0d;
        ImmutableTriple<Double, Double, Double>[] initialConditions = InitialConditions.getInitialConditions3D(
                -BOUNDARY, BOUNDARY, 30);

        AnalysisLauncher.open(new ScatterVisualization3D(
                new XFunction(),
                new YFunction(),
                new ZFunction(),
                initialConditions,
                BOUNDARY,
                STEP,
                START
        ));


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
            return 10 *(y - x);
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
            return x*(28-z)-y;
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
            return x*y - (8/3) * z;

        }
    }

}

