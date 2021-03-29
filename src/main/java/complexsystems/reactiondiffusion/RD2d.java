package complexsystems.reactiondiffusion;

import lombok.Getter;
import lombok.Setter;
import pdes.components.PdeRD2dU;
import pdes.components.PdeRD2dV;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Double.isFinite;
import static java.lang.Double.isNaN;

@Getter
@Setter
public class RD2d {

    private static final int INITIAL_MINIMUM_VALUE = 10;
    private double dt = 0.02;
    private double A = 3.5;
    private double B = 16;

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

    private PdeRD2dU pdeDuDt = new PdeRD2dU();
    private PdeRD2dV pdeDvDt = new PdeRD2dV();

    public RD2d(int width, int height) {
        this.width = width;
        this.height = height;

        u = new double[width][height];
        v = new double[width][height];
        uCopy = new double[width][height];
        vCopy = new double[width][height];
        randomizeArray(u);
        randomizeArray(v);
    }

    public void iterate() {

        System.arraycopy(u, 0, uCopy, 0, u.length);
        System.arraycopy(v, 0, vCopy, 0, u.length);

        for (int y = 0; y <= height - 1; y++) {
            for (int x = 0; x <= width - 1; x++) {

                pdeDuDt.calculateDuDt2d(uCopy, vCopy, x, y);
                dUdT = pdeDuDt.getdUdT();

                pdeDvDt.calculateDvDt2d(uCopy, vCopy, x, y);
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

    private void randomizeArray(double[][] arr) {
        Random rand = (new Random());
        IntStream.range(0, arr.length)
                .forEach(r -> IntStream.range(0, arr[0].length)
                        .forEach(c -> arr[r][c] = (0 + (INITIAL_MINIMUM_VALUE) * rand.nextDouble())));
    }
}