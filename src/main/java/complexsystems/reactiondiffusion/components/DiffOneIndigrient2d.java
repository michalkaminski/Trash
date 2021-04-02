package complexsystems.reactiondiffusion.components;

import lombok.Getter;
import lombok.Setter;
import pdes.components.PdeDiff2dU;
import pdes.components.PdeRD2dU;
import pdes.components.PdeRD2dV;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;

@Getter
@Setter
public class DiffOneIndigrient2d {

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

    private PdeDiff2dU pdeDuDt = new PdeDiff2dU();

    public DiffOneIndigrient2d(int width, int height) {
        this.width = width;
        this.height = height;

        u = new double[width][height];
        uCopy = new double[width][height];
        randomizeArray(u);
        }

    public void iterate() {

        System.arraycopy(u, 0, uCopy, 0, u.length);

        for (int y = 0; y <= height - 1; y++) {
            for (int x = 0; x <= width - 1; x++) {

                pdeDuDt.calculateDuDt2d(uCopy, x, y);
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

    private void randomizeArray(double[][] arr) {
        Random rand = (new Random());
        IntStream.range(0, arr.length)
                .forEach(r -> IntStream.range(0, arr[0].length)
                        .forEach(c -> arr[r][c] = (0 + (INITIAL_MINIMUM_VALUE) * rand.nextDouble())));
    }
}