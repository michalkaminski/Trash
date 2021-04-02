package complexsystems.reactiondiffusion.components;

import odes.components.visualizations.IterableArrayCalculation;
import lombok.Getter;
import lombok.Setter;
import pdes.components.PdeDiff2dU;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;

@Getter
@Setter
public class DiffOneIndigrient3d implements IterableArrayCalculation {

    private static final int INITIAL_MINIMUM_VALUE = 10;
    private double dt = 0.001;

    private double[][][] u;

    private double[][][] uCopy;

    private double dUdT;

    private double newU;

    private int size;

    private double min = 0;
    private double max = 0;

    private PdeDiff2dU pdeDuDt = new PdeDiff2dU();

    public DiffOneIndigrient3d(int size) {
        this.size = size;

        u = new double[size][size][size];
        uCopy = new double[size][size][size];
        randomizeArray(u);
    }

    @Override
    public void iterate() {

        System.arraycopy(u, 0, uCopy, 0, u.length);

        for (int y = 0; y <= size - 1; y++) {
            for (int x = 0; x <= size - 1; x++) {
                for (int z = 0; z <= size - 1; z++) {

                    pdeDuDt.calculateDuDt3d(uCopy, x, y, z);
                    dUdT = pdeDuDt.getdUdT();

                    newU = uCopy[x][y][z] + dUdT * dt;
                    newU = newU < 0 ? 0 : newU;

                    if (!isNaN(newU) && isFinite(newU))
                        u[x][y][z] = newU;

                    if (max < u[x][y][z]) max = u[x][y][z];
                    if (min > u[x][y][z]) min = u[x][y][z];
                }
            }
        }

    }

    private void randomizeArray(double[][][] arr) {
        Random rand = (new Random());
        IntStream.range(0, arr.length)
                .forEach(r -> IntStream.range(0, arr[0].length)
                        .forEach(c -> IntStream.range(0, arr[0][0].length)
                                .forEach(z -> arr[r][c][z] = (0 + (INITIAL_MINIMUM_VALUE) * rand.nextDouble()))));
    }
}