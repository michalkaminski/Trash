package complexsystems.reactiondiffusion.components;

import lombok.Getter;
import lombok.Setter;
import odes.components.visualizations.IterableArrayCalculation;
import pdes.components.PdeDiffussionOneIndigrient;

import static complexsystems.arrays.RandomizeArrayUtils.randomizeArray;
import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;

@Getter
@Setter
public class DiffOneIndigrient2d implements IterableArrayCalculation {

    private static final int INITIAL_MINIMUM_VALUE = 10;
    private double dt = 0.001;

    private double[][] u;

    private double[][] uCopy;

    private double dUdT;

    private double newU;

    private int width;
    private int height;

    private double min = 0;
    private double max = 0;

    private PdeDiffussionOneIndigrient pdeDuDt = new PdeDiffussionOneIndigrient();

    public DiffOneIndigrient2d(int width, int height) {
        this.width = width;
        this.height = height;

        u = new double[width][height];
        uCopy = new double[width][height];
        randomizeArray(u, INITIAL_MINIMUM_VALUE);
    }

    public void iterate() {

        System.arraycopy(u, 0, uCopy, 0, u.length);

        for (int y = 0; y <= height - 1; y++) {
            for (int x = 0; x <= width - 1; x++) {

                pdeDuDt.calculateDuDt(uCopy, x, y);
                dUdT = pdeDuDt.getdUdT();

                newU = uCopy[x][y] + dUdT * dt;
                newU = newU < 0 ? 0 : newU;

                if (!isNaN(newU) && isFinite(newU))
                    u[x][y] = newU;

                if (max < u[x][y]) max = u[x][y];
                if (min > u[x][y]) min = u[x][y];
            }
        }

    }

    @Override
    public double[][][] getU3d() {
        return null;
    }

    @Override
    public double[][] getU2d() {
        return getU();
    }

}