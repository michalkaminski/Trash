package complexsystems.reactiondiffusion.components;

import lombok.Getter;
import lombok.Setter;
import odes.components.visualizations.IterableArrayCalculation;
import pdes.components.PdeRDTwoIndigrientsU;
import pdes.components.PdeRDTwoIndigrientsV;

import static complexsystems.arrays.ArrayUtils.randomizeArray;
import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;

@Getter
@Setter
public class DiffTwoIndigrients3d implements IterableArrayCalculation {

    private static final int INITIAL_MINIMUM_VALUE = 10;
    private double dt = 0.01;
    private double[][][] u;
    private double[][][] v;

    private double[][][] uCopy;
    private double[][][] vCopy;

    private double dUdT;
    private double dVdT;

    private double newU;
    private double newV;

    private int size;

    private double min = 0;
    private double max = 0;

    private PdeRDTwoIndigrientsU pdeDuDt = new PdeRDTwoIndigrientsU();
    private PdeRDTwoIndigrientsV pdeDvDt = new PdeRDTwoIndigrientsV();

    public DiffTwoIndigrients3d(int size) {
        this.size = size;

        u = new double[size][size][size];
        v = new double[size][size][size];
        uCopy = new double[size][size][size];
        vCopy = new double[size][size][size];
        randomizeArray(u, INITIAL_MINIMUM_VALUE);
        randomizeArray(v, INITIAL_MINIMUM_VALUE);
    }

    @Override
    public void iterate() {

        System.arraycopy(u, 0, uCopy, 0, u.length);
        System.arraycopy(v, 0, vCopy, 0, u.length);

        for (int y = 0; y <= size - 1; y++) {
            for (int x = 0; x <= size - 1; x++) {
                for (int z = 0; z <= size - 1; z++) {

                    pdeDuDt.calculateDuDt3d(uCopy, vCopy, x, y, z);
                    dUdT = pdeDuDt.getdUdT();

                    pdeDvDt.calculateDuDt(uCopy, vCopy, x, y, z);
                    dVdT = pdeDvDt.getdUdT();

                    newU = uCopy[x][y][z] + dUdT * dt;
                    newU = newU < 0 ? 0 : newU;

                    if (!isNaN(newU) && isFinite(newU))
                        u[x][y][z] = newU;

                    newV = vCopy[x][y][z] + dVdT * dt;
                    newV = newV < 0 ? 0 : newV;

                    if (!isNaN(newV) && isFinite(newV))
                        v[x][y][z] = newV;

                    if (max < u[x][y][z]) max = u[x][y][z];
                    if (min > u[x][y][z]) min = u[x][y][z];
                }
            }
        }

    }

    @Override
    public double[][][] getU3d() {
        return getU();
    }

    @Override
    public double[][] getU2d() {
        return null;
    }
}