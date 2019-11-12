package libra.odes.odes;

import javafx.util.Pair;
import libra.functions.IFunction;
import libra.functions.PredatorFunction;
import libra.functions.PreyFunction;
import libra.initialcondition.InitialConditions;
import libra.odes.visual.ScatterVisualization2D;
import org.jzy3d.analysis.AnalysisLauncher;

public class PredatorPreyVF {

    public static void main(String[] args) throws Exception {
        IFunction functionX = new PreyFunction();
        IFunction functionY = new PredatorFunction();

        Pair<Double, Double>[] initialConditions =
                InitialConditions.getInitialConditions(0, 100, 10);


        double SCALE = 100;
        double STEP = 0.01d;
        double START = 0d;

//        EulerVisualizationVF2D vis = new EulerVisualizationVF2D(
//                functionX,
//                functionY,
//                initialConditions,
//                SCALE,
//                STEP,
//                START
//        );
//        vis.visualize();

        AnalysisLauncher.open(new ScatterVisualization2D(
                functionX,
                functionY,
                initialConditions,
                SCALE,
                STEP,
                START
        ));



}
}
