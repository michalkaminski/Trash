package complexsystems.reactiondiffusion;

import odes.demos.Vis3D3D;
import org.jzy3d.analysis.AnalysisLauncher;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RdRunner3d {

    public static void main(String[] args) throws Exception {

        int size = 50;

        RD3d rd3d = new RD3d(size);
        Vis3D3D vis3d = new Vis3D3D(size);
        AnalysisLauncher.open(vis3d);

        ExecutorService executor = Executors.newWorkStealingPool(2);
        Future<double[][][]> future;

        Callable<double[][][]> task = () -> {
            rd3d.iterate();
            return rd3d.getU();
        };

        int i = 0;
        while (true) {
            future = executor.submit(task);
            vis3d.generateFrom3dArray(future.get(), rd3d.getMin(), rd3d.getMax());
            vis3d.render();
            System.out.println(i++);
        }
    }

}
