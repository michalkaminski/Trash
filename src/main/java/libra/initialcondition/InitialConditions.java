package libra.initialcondition;

import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;

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


    private static List<ImmutableTriple<Double,Double,Double>> getInitialCondition3D(double min, double max, int size) {
        double[] initialConditionX = DoubleStream.generate(() ->  Math.random()*(max-(min))+(min)).limit(size).toArray();
        double[] initialConditionY = DoubleStream.generate(() ->  Math.random()*(max-(min))+(min)).limit(size).toArray();
        double[] initialConditionZ = DoubleStream.generate(() ->  Math.random()*(max-(min))+(min)).limit(size).toArray();

        List<ImmutableTriple<Double, Double, Double>> initialConditionList = new ArrayList<>();
        for (int i = 0; i < initialConditionX.length - 1; i++) {
            initialConditionList.add(new ImmutableTriple(initialConditionX[i], initialConditionY[i], initialConditionZ[i]));
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

    public static ImmutableTriple<Double,Double, Double>[] getInitialConditions3D(double min, double max, int size)
    {
        List<ImmutableTriple<Double, Double, Double>> initialConditionList = getInitialCondition3D(min, max, size);
        ImmutableTriple[] initialConditions = initialConditionList.toArray(new ImmutableTriple[initialConditionList.size()]);
        return initialConditions;
    }
}
