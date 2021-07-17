package pdes.components;

import odes.components.laplacian.DiscreateLaplacian;

public class PdeDiffussionOneIndigrient implements IpdeOneIndigrient {

    private double dUdT = 0.0;

    private DiscreateLaplacian lap = new DiscreateLaplacian();

    public PdeDiffussionOneIndigrient() {
    }

    @Override
    public double getdUdT() {
        return dUdT;
    }

    @Override
    public void calculateDuDt(double[][] u, int x, int y) {
        dUdT = lap.getLaplacian2D(u, x, y);
    }

    @Override
    public void calculateDuDt(double[][][] u, int x, int y, int z) {
        dUdT = lap.getLaplacian3D(u, x, y, z);
    }

    @Override
    public void calculateDuDt(double[][] u, double[][] v, int x, int y) {

    }

    @Override
    public void calculateDuDt(double[][][] u, double[][][] v, int x, int y, int z) {

    }


}
