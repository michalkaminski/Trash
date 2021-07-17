package complexsystems.reactiondiffusion.runners;

import complexsystems.reactiondiffusion.components.DiffTwoIndigrients2d;
import odes.components.visualizations.ArrayVis2D;

import java.util.concurrent.*;

public class TwoIndigrients2dRun {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 400;
        DiffTwoIndigrients2d diffTwoIndigrients2D = new DiffTwoIndigrients2d(size, size);
        ArrayVis2D screen = new ArrayVis2D(size, size);
        screen.visualize(size, diffTwoIndigrients2D);
    }
}
