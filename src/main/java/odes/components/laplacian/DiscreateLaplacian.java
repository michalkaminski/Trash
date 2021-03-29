package odes.components.laplacian;

import static java.lang.Math.pow;

/**
 * discrete spatial derivatives (Laplacians)
 */
public class DiscreateLaplacian {

    /**
     * 1D
     * L(U)(X) = ( + 2 U(X) -   U(X-H) -   U(X+H) ) / H^2
     * Because of side effects on the edges of the matrix, we need to remove the borders of the grid in the computation
     */

    public double getLaplacian1D(double U[], int x) {
        int h = 1;
        //TODO to be implemented
        return (
                +2 * U[x] -
                     U[x - h] -
                     U[x + h])
                / pow(h, 2);
    }


    /**
     * 2D we define a function that computes the discrete Laplace operator of a 2D variable on the grid
     * L(U)(X,Y) = ( + 4 U(X,Y)  -   U(X-H,Y-H) - U(X-H,Y+H)  -   U(X+H,Y-H) - U(X+H,Y+H) ) / H^2
     * Because of side effects on the edges of the matrix, we need to remove the borders of the grid in the computation
     */

    public double getLaplacian2D(double U[][], int x, int y) {
        int h = 1;
        int size = U.length;

        int x1 = x + 1;
        int x0 = x - 1;
        int y1 = y + 1;
        int y0 = y - 1;

        if (x == 0) x0 = size - 1;
        if (x == size - 1) x1 = 0;
        if (y == 0) y0 = size - 1;
        if (y == size - 1) y1 = 0;

        return (-4.0 * U[x][y] + U[x1][y] + U[x0][y] + U[x][y1] + U[x][y0]);


//        int x0 = Math.max(x - 1, 0);
//        int x1 = Math.min(x + 1, size - 1);
//        int y0 = Math.max(y - 1, 0);
//        int y1 = Math.min(y + 1, size - 1);
//
//        return   (U[x][y0]
//                + U[x][y1]
//                + U[x0][y]
//                + U[x1][y]
//                - 4 * U[x][y])
//                / pow(h, 2);


//        return (
//                +4 * U[x][y] -
//                        U[x - h][y - h] -
//                        U[x - h][y + h] -
//                        U[x + h][y - h] -
//                        U[x + h][y + h])
//                / pow(h, 2);


    }

    //TODO
//    public double getLaplacian3D(double U[][][], int x, int y, int z, int h) {
//        if (((x + h) >= (U.length)) || (x - h < 0)) return 0d;
//        if (((y + h) >= (U[0].length)) || (y - h < 0)) return 0d;
//        return (
//                +   4 * U[x][y][z] -
//                        U[x - h][y - h] -
//                        U[x - h][y + h] -
//                        U[x + h][y - h] -
//                        U[x + h][y + h])
//                / pow(h, 2);
//    }


    public double getLaplacian3D(double U[][][], int x, int y, int z) {
        int size = U.length;

        int x1 = x + 1;
        int x0 = x - 1;
        int y1 = y + 1;
        int y0 = y - 1;
        int z1 = z + 1;
        int z0 = z - 1;

        if (x == 0) x0 = size - 1;
        if (x == size - 1) x1 = 0;
        if (y == 0) y0 = size - 1;
        if (y == size - 1) y1 = 0;
        if (z == 0) z0 = size - 1;
        if (z == size - 1) z1 = 0;

        return (
           -8.0 * U[x][y][z] +

                  U[x0][y0][z0] +
                  U[x0][y1][z0] +
                  U[x0][y0][z1] +
                  U[x0][y1][z1] +

                  U[x1][y0][z0] +
                  U[x1][y1][z0] +
                  U[x1][y0][z1] +
                  U[x1][y1][z1]

        );
    }

    }
