package complexsystems.reactiondiffusion.runners;

import complexsystems.reactiondiffusion.components.DiffTwoIndigrients2d;
import odes.components.visualizations.ArrayVis2D;

import java.util.concurrent.*;

public class TwoIndigrients2dRun {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int size = 400;

        DiffTwoIndigrients2d diffTwoIndigrients2D = new DiffTwoIndigrients2d(size, size);
        ArrayVis2D screen = new ArrayVis2D(size, size);

        ExecutorService executor = Executors.newWorkStealingPool(5);
        Future<double[][]> future;

        Callable<double[][]> task = () -> {
            diffTwoIndigrients2D.iterate();
            return diffTwoIndigrients2D.getU();
        };

        int i = 0;
        while (true) {
            future = executor.submit(task);
            screen.setSpace2d(future.get());
            screen.setMin(diffTwoIndigrients2D.getMin());
            screen.setMax(diffTwoIndigrients2D.getMax());
            screen.repaint();
            System.out.println(i++);
        }
    }

}
