package odes.components.visualizations;

public interface IterableArrayCalculation {

    void iterate();

    double[][][] getU3d();

    double[][] getU2d();

    double getMin();

    double getMax();
}
