package complexsystems.reactiondiffusion.runners;

import complexsystems.reactiondiffusion.components.DiffOneIndigrient2d;
import odes.components.visualizations.ArrayVis2D;

import java.util.concurrent.ExecutionException;

public class OneIndigrient2dRun {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int size = 400;
        DiffOneIndigrient2d diffOneIndigrient2d = new DiffOneIndigrient2d(size, size);
        ArrayVis2D screen = new ArrayVis2D(size, size);
        screen.visualize(size, diffOneIndigrient2d);
    }
}