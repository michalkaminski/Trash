package pdes.components;

import odes.components.laplacian.DiscreateLaplacian;

public class PdeRD2dU implements Ipde2d {

    private double dUdT = 0.0;
    private double diffRate = 3.5;

    private DiscreateLaplacian lap = new DiscreateLaplacian();

    public PdeRD2dU() {
    }

    public void calculateDuDt(double[][] u, double[][] v, int x, int y) {
        dUdT = f(u[x][y], v[x][y]) + diffRate * lap.getLaplacian2D(u, x, y);
    }

    @Override
    public double getdUdT() {
        return dUdT;
    }

    private double f(double u, double v) {
        return u * (v - 1) - 12;
    }


}
