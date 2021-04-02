package complexsystems.reactiondiffusion.runners;

import complexsystems.reactiondiffusion.components.DiffOneIndigrient2d;
import odes.components.visualizations.ArrayVis2D;

import java.util.concurrent.*;

public class OneIndigrient2dRun {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int size = 400;

        DiffOneIndigrient2d rd2d = new DiffOneIndigrient2d(size, size);
        ArrayVis2D screen = new ArrayVis2D(size, size);

        ExecutorService executor = Executors.newWorkStealingPool(5);
        Future<double[][]> future;

        Callable<double[][]> task = () -> {
            rd2d.iterate();
            return rd2d.getU();
        };

        int i = 0;
        while (true) {
            future = executor.submit(task);
            screen.setSpace2d(future.get());
            screen.setMin(rd2d.getMin());
            screen.setMax(rd2d.getMax());
            screen.repaint();
            System.out.println(i++);
        }
    }

}
