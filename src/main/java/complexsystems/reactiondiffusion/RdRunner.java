package complexsystems.reactiondiffusion;

import odes.components.visualizations.ArrayVis2D;

import java.util.concurrent.*;

public class RdRunner {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        int size = 400;

        RD rd = new RD(size, size);
        ArrayVis2D screen = new ArrayVis2D(size, size);

        ExecutorService executor = Executors.newWorkStealingPool(5);
        Future<double[][]> future;

        Callable<double[][]> task = () -> {
            rd.iterate();
            return rd.getU();
        };

        int i = 0;
        while (true) {
            future = executor.submit(task);
            screen.setSpace2d(future.get());
            screen.setMin(rd.getMin());
            screen.setMax(rd.getMax());
            screen.repaint();
            System.out.println(i++);
        }
    }

}
