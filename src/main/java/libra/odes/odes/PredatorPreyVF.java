package libra.odes.odes;

import javafx.util.Pair;
import libra.functions.IFunction;
import libra.functions.PredatorFunction;
import libra.functions.PreyFunction;
import libra.odes.visual.EulerVisualizationVF2D;

public class PredatorPreyVF {

    public static void main(String[] args)
    {
        IFunction functionX=new PreyFunction();
        IFunction functionY=new PredatorFunction();

        Pair[] initialCondition = {
                new Pair (1d, 10d), new Pair (5d, 10d), new Pair (10d, 10d), new Pair (20d, 10d)
        };

        double SCALE= 30;
        double STEP=0.0001d;
        double START=0d;

        EulerVisualizationVF2D vis=new EulerVisualizationVF2D(
                functionX,
                functionY,
                initialCondition,
                SCALE,
                STEP,
                START
        );
        vis.visualize();
    }
}
