package libra.odes.odes;

import javafx.util.Pair;
import libra.functions.*;
import libra.odes.visual.EulerVisualizationVF2D;

public class Ode1VF {

    public static void main(String[] args)
    {
        IFunction functionX=new SinXFunction();
        IFunction functionY=new CosXFunction();

        Pair[] initialCondition = {
                new Pair (1d, 1d), new Pair (1d, 2d), new Pair (0.5d, 0.5d), new Pair (1d, 1d)
        };

        double SCALE= 10;
        double STEP=0.00001d;
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
