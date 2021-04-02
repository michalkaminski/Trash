package odes.components.visualizations;

import odes.demos.Vis3D3D;
import org.jzy3d.analysis.AnalysisLauncher;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ArrayVis3D {

    public void visualize(int size, IterableArrayCalculation iterableArrayCalculation) throws Exception {

        Vis3D3D vis3d = new Vis3D3D(size);
        AnalysisLauncher.open(vis3d);

        ExecutorService executor = Executors.newWorkStealingPool(2);
        Future<double[][][]> future;

        Callable<double[][][]> task = () -> {
            iterableArrayCalculation.iterate();
            return iterableArrayCalculation.getU();
        };

        int i = 0;
        while (true) {
            future = executor.submit(task);
            vis3d.generateFrom3dArray(future.get(), iterableArrayCalculation.getMin(), iterableArrayCalculation.getMax());
            vis3d.render();
            System.out.println(i++);
        }
    }
}
