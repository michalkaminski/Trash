package libra.initialcondition;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class InitialConditions {

    private static List<Pair<Double,Double>> getInitialCondition(double min, double max, int size) {
        double[] initialConditionX = DoubleStream.generate(() ->  Math.random()*(max-(min))+(min)).limit(size).toArray();
        double[] initialConditionY = DoubleStream.generate(() ->  Math.random()*(max-(min))+(min)).limit(size).toArray();
        List<Pair<Double, Double>> initialConditionList = new ArrayList<>();
        for (int i = 0; i < initialConditionX.length - 1; i++) {
            initialConditionList.add(new Pair(initialConditionX[i], initialConditionY[i]));
            i++;
        }
        return initialConditionList;
    }

    public static Pair<Double,Double>[] getInitialConditions(double min, double max, int size)
    {
        List<Pair<Double, Double>> initialConditionList = getInitialCondition(min, max, size);
        Pair[] initialConditions = initialConditionList.toArray(new Pair[initialConditionList.size()]);
        return initialConditions;
    }
}
