package pdes.components;

import odes.components.laplacian.DiscreateLaplacian;

public class PdeDiff2dU implements Ipde2d {

    private double dUdT = 0.0;

    private DiscreateLaplacian lap = new DiscreateLaplacian();

    public PdeDiff2dU() {
    }

    public void calculateDuDt2d(double[][] u, int x, int y) {

        dUdT = lap.getLaplacian2D(u, x, y);
    }

    public void calculateDuDt3d(double[][][] u, int x, int y, int z) {
        dUdT = lap.getLaplacian3D(u, x, y, z);
    }


    @Override
    public double getdUdT() {
        return dUdT;
    }


}
