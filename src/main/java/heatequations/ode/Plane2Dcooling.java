package heatequations.ode;

import odes.components.functions.FunctionNewtonCooling20C;
import odes.components.functions.IFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Plane2Dcooling {
    static IFunction function = new FunctionNewtonCooling20C();

    public static void main(String args[]) throws IOException {

        BitmapOperations bo = new BitmapOperations();
        int[][] plane = bo.readBitmap("2dCooling.bmp");
        bo.saveBitmap(plane, "d:\\plane.bmp");


        List<Point> planePoints = new ArrayList<>();
        for (int w = 0; w < plane.length - 1; w++) {
            for (int h = 0; h < plane[w].length - 1; h++) {
                planePoints.add(new Point(w, h, plane[w][h]));
            }
        }

        Screen screen = new Screen(70, 70, planePoints);

        while (true) {
            iterate(planePoints);
            screen.repaint();
        }
    }

    private static void iterate(List<Point> planePoints) {
        for (int i = 0; i <= planePoints.size() - 1; i++) {

            Point l;
            Point r;
            Point c = planePoints.get(i);
            if (i == 0) {
                l = c;
            } else {
                l = planePoints.get(i - 1);
            }
            if (i == planePoints.size() - 1) {
                r = c;
            } else {
                r = planePoints.get(i + 1);
            }
            //TODO implement 2d laplacian istead of this derivative

            double T = c.getTx() + function.secondDerivative(l.getTx(), c.getTx(), r.getTx());
//            double T = c.getTx() + (((l.getTx() + r.getTx()) / 2) - c.getTx());
            c.setTx(T);
        }
    }
}
