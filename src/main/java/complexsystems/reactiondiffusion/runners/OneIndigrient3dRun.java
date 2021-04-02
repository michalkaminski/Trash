package complexsystems.reactiondiffusion.runners;

import complexsystems.reactiondiffusion.components.DiffOneIndigrient3d;
import odes.components.visualizations.ArrayVis3D;

public class OneIndigrient3dRun {

    public static void main(String[] args) throws Exception {
        int size = 70;
        ArrayVis3D arrayVis3D = new ArrayVis3D();
        arrayVis3D.visualize(size, new DiffOneIndigrient3d(size));
    }
}
