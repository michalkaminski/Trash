package complexsystems.reactiondiffusion.components;

import lombok.Getter;
import lombok.Setter;
import odes.components.visualizations.IterableArrayCalculation;
import pdes.components.PdeRDTwoIndigrientsU;
import pdes.components.PdeRDTwoIndigrientsV;

import static complexsystems.arrays.RandomizeArrayUtils.randomizeArray;
import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;
import static java.lang.System.arraycopy;

@Getter
@Setter
public class DiffTwoIndigrients2d implements IterableArrayCalculation {

    private static final int INITIAL_MINIMUM_VALUE = 10;
    private double dt = 0.02;

    private double[][] u;
    private double[][] v;

    private double[][] uCopy;
    private double[][] vCopy;

    private double dUdT;
    private double dVdT;

    private double newU;
    private double newV;

    private int width;
    private int height;

    private double min = 0;
    private double max = 0;

    private PdeRDTwoIndigrientsU pdeDuDt = new PdeRDTwoIndigrientsU();
    private PdeRDTwoIndigrientsV pdeDvDt = new PdeRDTwoIndigrientsV();

    public DiffTwoIndigrients2d(int width, int height) {
        this.width = width;
        this.height = height;

        u = new double[width][height];
        v = new double[width][height];
        uCopy = new double[width][height];
        vCopy = new double[width][height];
        randomizeArray(u, INITIAL_MINIMUM_VALUE);
        randomizeArray(v, INITIAL_MINIMUM_VALUE);
    }

    public void iterate() {

        arraycopy(u, 0, uCopy, 0, u.length);
        arraycopy(v, 0, vCopy, 0, u.length);

        for (int y = 0; y <= height - 1; y++) {
            for (int x = 0; x <= width - 1; x++) {

                pdeDuDt.calculateDuDt(uCopy, vCopy, x, y);
                dUdT = pdeDuDt.getdUdT();

                pdeDvDt.calculateDuDt(uCopy, vCopy, x, y);
                dVdT = pdeDvDt.getdUdT();

                newU = uCopy[x][y] + dUdT * dt;
                newU = newU < 0 ? 0 : newU;

                if (!isNaN(newU) && isFinite(newU))
                    u[x][y] = newU;

                newV = vCopy[x][y] + dVdT * dt;
                newV = newV < 0 ? 0 : newV;

                if (!isNaN(newV) && isFinite(newV))
                    v[x][y] = newV;

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