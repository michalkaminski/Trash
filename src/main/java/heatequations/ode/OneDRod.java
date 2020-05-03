package heatequations.ode;

import javafx.util.Pair;
import odes.components.functions.FunctionNewtonCooling20C;
import odes.components.functions.IFunction;
import odes.components.functions.initialcondition.InitialConditions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OneDRod {

    static List<Point> rodPoints;

    static Pair<Double, Double>[] initialConditions = InitialConditions.getInitialConditionsSin(-15, 80000, 0.01, 50);
    static IFunction function = new FunctionNewtonCooling20C();

    public static void main(String[] args) {

        rodPoints = Arrays.stream(initialConditions).map(
                pair -> new Point(pair.getKey(), pair.getValue())
        ).collect(Collectors.toList());

        Screen screen = new Screen(800, 500, rodPoints);
        screen.setAlignmentX(0f);
        screen.setAlignmentY(0f);

        while (true) {
            iterate(rodPoints);
            screen.repaint();
        }
    }

    private static void iterate(List<Point> rodPoints) {
        for (int i = 0; i <= rodPoints.size() - 1; i++) {

            Point l;
            Point r;
            Point c = rodPoints.get(i);
            if (i == 0) {
                l = c;
            } else {
                l = rodPoints.get(i - 1);
            }
            if (i == rodPoints.size() - 1) {
                r = c;
            } else {
                r = rodPoints.get(i + 1);
            }
            double T = c.getTx() + function.secondDerivative(l.getTx(), c.getTx(), r.getTx());
//            double T = c.getTx() + (((l.getTx() + r.getTx()) / 2) - c.getTx());
            c.setTx(T);
        }
    }

}
