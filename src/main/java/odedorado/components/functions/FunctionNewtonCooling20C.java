package odedorado.components.functions;


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
        return 0.2*(20-T);
    }
}
