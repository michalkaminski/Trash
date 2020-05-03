package odes.components.functions.initialcondition;

import javafx.util.Pair;
import org.apache.commons.lang3.tuple.ImmutableTriple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class InitialConditions {

    private static List<Pair<Double, Double>> getInitialCondition2D(double min, double max, int size) {
        double[] initialConditionX = DoubleStream.generate(() -> Math.random() * (max - (min)) + (min)).limit(size).toArray();
        double[] initialConditionY = DoubleStream.generate(() -> Math.random() * (max - (min)) + (min)).limit(size).toArray();
        List<Pair<Double, Double>> initialConditionList = new ArrayList<>();
        for (int i = 0; i < initialConditionX.length - 1; i++) {
            initialConditionList.add(new Pair(initialConditionX[i], initialConditionY[i]));
            i++;
        }
        return initialConditionList;
    }


    private static List<ImmutableTriple<Double, Double, Double>> getInitialCondition3D(double min, double max, int size) {
        double[] initialConditionX = DoubleStream.generate(() -> Math.random() * (max - (min)) + (min)).limit(size).toArray();
        double[] initialConditionY = DoubleStream.generate(() -> Math.random() * (max - (min)) + (min)).limit(size).toArray();
        double[] initialConditionZ = DoubleStream.generate(() -> Math.random() * (max - (min)) + (min)).limit(size).toArray();

        List<ImmutableTriple<Double, Double, Double>> initialConditionList = new ArrayList<>();
        for (int i = 0; i < initialConditionX.length - 1; i++) {
            initialConditionList.add(new ImmutableTriple(initialConditionX[i], initialConditionY[i], initialConditionZ[i]));
            i++;
        }
        return initialConditionList;
    }


    public static Pair<Double, Double>[] getInitialConditions(double min, double max, int size) {
        List<Pair<Double, Double>> initialConditionList = getInitialCondition2D(min, max, size);
        Pair[] initialConditions = initialConditionList.toArray(new Pair[initialConditionList.size()]);
        return initialConditions;
    }

    public static ImmutableTriple<Double, Double, Double>[] getInitialConditions3D(double min, double max, int size) {
        List<ImmutableTriple<Double, Double, Double>> initialConditionList = getInitialCondition3D(min, max, size);
        ImmutableTriple[] initialConditions = initialConditionList.toArray(new ImmutableTriple[initialConditionList.size()]);
        return initialConditions;
    }

    public static Pair<Double, Double>[] getInitialConditionsSin(double min, int size, double step, double factor) {

        double[] initialConditionX = DoubleStream.iterate(min, n -> n + step).limit(size).toArray();
        List<Double> initialConditionY = Arrays.stream(initialConditionX).boxed()
                .map(x -> Math.sin(x)).collect(Collectors.toList());

        List<Pair<Double, Double>> initialConditionList = new ArrayList<>();
        for (int i = 0; i < initialConditionX.length - 1; i++) {
            initialConditionList.add(new Pair(initialConditionX[i] * factor, initialConditionY.get(i) * factor));
            i++;
        }

        Pair[] initialConditions = initialConditionList.toArray(new Pair[initialConditionList.size()]);
        return initialConditions;
    }

}
