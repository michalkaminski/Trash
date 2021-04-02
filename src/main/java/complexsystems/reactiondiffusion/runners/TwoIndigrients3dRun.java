package complexsystems.reactiondiffusion.runners;

import complexsystems.reactiondiffusion.components.DiffTwoIndigrients3d;
import odes.components.visualizations.ArrayVis3D;

public class TwoIndigrients3dRun {

    public static void main(String[] args) throws Exception {
        int size = 50;
        ArrayVis3D arrayVis3D = new ArrayVis3D();
        arrayVis3D.visualize(size, new DiffTwoIndigrients3d(size));
    }
}
