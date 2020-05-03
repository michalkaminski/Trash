package odes.components.functions;


public class FunctionNewtonCooling20C implements IFunction {
    @Override
    public double valueOf(double... arg) {
        double x = arg[0];
        return 0;
    }

    @Override
    public double derivative(double... arg) {
        /** dT/dt = 0.2(20-T) */
        double T = arg[0];
        return 0.2 * (20 - T);
    }

    @Override
    public double secondDerivative(double... arg) {
        double lT = arg[0];
        double cT = arg[1];
        double rT = arg[2];
//        return (cT - lT) - (rT - cT);
        return (((lT + rT) / 2) - cT);
    }
}
