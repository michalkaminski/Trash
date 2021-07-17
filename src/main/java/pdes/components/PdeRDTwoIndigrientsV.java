package pdes.components;

import odes.components.laplacian.DiscreateLaplacian;

public class PdeRDTwoIndigrientsV implements IpdeOneIndigrient {

    private double dUdT = 0.0;
    private double diffRate = 16.0;

    private DiscreateLaplacian lap = new DiscreateLaplacian();

    public PdeRDTwoIndigrientsV() {}

    public void calculateDuDt(double[][] u, double[][] v, int x, int y) {
        dUdT = g(u[x][y], v[x][y]) + diffRate * lap.getLaplacian2D(v, x, y);
    }

    public void calculateDuDt(double[][][] u, double[][][] v, int x, int y, int z) {
        dUdT = g(u[x][y][z], v[x][y][z]) + diffRate * lap.getLaplacian3D(v, x, y, z);
    }

    @Override
    public double getdUdT() {
        return dUdT;
    }

    @Override
    public void calculateDuDt(double[][] u, int x, int y) {

    }

    @Override
    public void calculateDuDt(double[][][] u, int x, int y, int z) {

    }

    private double g(double u, double v) {
        return 16 - u * v;
    }


}
