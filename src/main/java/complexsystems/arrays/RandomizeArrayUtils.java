package complexsystems.arrays;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomizeArrayUtils {

    public static void randomizeArray(double[][] arr, double INITIAL_MINIMUM_VALUE) {
        Random rand = (new Random());
        IntStream.range(0, arr.length)
                .forEach(r -> IntStream.range(0, arr[0].length)
                        .forEach(c -> arr[r][c] = (0 + (INITIAL_MINIMUM_VALUE) * rand.nextDouble())));
    }

    public static void randomizeArray(double[][][] arr, double INITIAL_MINIMUM_VALUE) {
        Random rand = (new Random());
        IntStream.range(0, arr.length)
                .forEach(r -> IntStream.range(0, arr[0].length)
                        .forEach(c -> IntStream.range(0, arr[0][0].length)
                                .forEach(z -> arr[r][c][z] = (0 + (INITIAL_MINIMUM_VALUE) * rand.nextDouble()))));
    }

}
