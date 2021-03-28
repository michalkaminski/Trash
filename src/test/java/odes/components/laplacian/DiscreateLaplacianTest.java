package odes.components.laplacian;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscreateLaplacianTest {

    @Test
    void getLaplacian1D() {

        double space1D[] = {4, 2, 3};
        DiscreateLaplacian dl = new DiscreateLaplacian();
        double laplacian = dl.getLaplacian1D(space1D, 1);

        assertEquals(-3, laplacian);

        laplacian = dl.getLaplacian1D(space1D, 0);
        assertEquals(0, laplacian);

        laplacian = dl.getLaplacian1D(space1D, 2);
        assertEquals(0, laplacian);

    }


    @Test
    void getLaplacian2DValueTest() {
        double space2D[][] = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        DiscreateLaplacian dl = new DiscreateLaplacian();
        double laplacian = dl.getLaplacian2D(space2D, 0, 0);
        assertEquals(4, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 0, 1);
        assertEquals(3, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 0, 2);
        assertEquals(2, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 1, 0);
        assertEquals(1, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 1, 1);
        assertEquals(0, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 2, 0);
        assertEquals(-2, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 2, 1);
        assertEquals(-3, laplacian);

        laplacian = dl.getLaplacian2D(space2D, 2, 2);
        assertEquals(-4, laplacian);
    }

}