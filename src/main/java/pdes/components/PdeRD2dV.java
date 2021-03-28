package pdes.components;

import odes.components.laplacian.DiscreateLaplacian;

public class PdeRD2dV implements Ipde2d {

    private double dUdT = 0.0;
    private double diffRate = 16.0;

    private DiscreateLaplacian lap = new DiscreateLaplacian();

    public PdeRD2dV() {}

    public void calculateDvDt(double[][] u, double[][] v, int x, int y) {
        dUdT = g(u[x][y], v[x][y]) + diffRate * lap.getLaplacian2D(v, x, y);
    }

    @Override
    public double getdUdT() {
        return dUdT;
    }

    private double g(double u, double v) {
        return 16 - u * v;
    }


}
