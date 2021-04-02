package odes.components.visualizations;

public interface IterableArrayCalculation {

    void iterate();

    double[][][] getU();

    double getMin();

    double getMax();
}
