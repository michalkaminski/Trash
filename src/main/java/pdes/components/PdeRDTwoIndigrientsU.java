package pdes.components;

import odes.components.laplacian.DiscreateLaplacian;

public class PdeRDTwoIndigrientsU implements IpdeOneIndigrient {

    private double dUdT = 0.0;
    private double diffRate = 1;

    private DiscreateLaplacian lap = new DiscreateLaplacian();

    public PdeRDTwoIndigrientsU() {
    }

    public void calculateDuDt(double[][] u, double[][] v, int x, int y) {
        dUdT = f(u[x][y], v[x][y]) + diffRate * lap.getLaplacian2D(u, x, y);
    }

    public void calculateDuDt3d(double[][][] u, double[][][] v, int x, int y, int z) {
        dUdT = f(u[x][y][z], v[x][y][z]) + diffRate * lap.getLaplacian3D(u, x, y, z);
    }

    @Override
    public void calculateDuDt(double[][][] u, double[][][] v, int x, int y, int z) {

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

    private double f(double u, double v) {
        return u * (v - 1) - 12;
    }
}
