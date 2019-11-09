package libra.odes.odes;

import libra.functions.FunctionNewtonCooling20C;
import libra.functions.IFunction;
import libra.odes.visual.EulerVisualization;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class NewtonCooling20Ode {

    public static void main(String[] args)
    {
        IFunction function=new FunctionNewtonCooling20C();
        double initialCondition[]= DoubleStream.generate(() ->  Math.random()*(100-(-100))+(-100)).limit(50).toArray();

        double SCALE= Arrays.stream(initialCondition).max().getAsDouble();
        double STEP=0.0001d;
        double START=0d;

        EulerVisualization vis=new EulerVisualization(
                function,
                initialCondition,
                SCALE,
                STEP,
                START
        );
        vis.visualize();
    }
}
