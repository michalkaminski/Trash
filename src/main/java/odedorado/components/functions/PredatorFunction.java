package odedorado.components.functions;


public class PredatorFunction implements IFunction {
    @Override
    public double valueOf(double... arg) {
        double x = arg[0];
        return x;
    }

    @Override
    public double derivative(double... arg) {
        double y1 = arg[0];
        double y2= arg[1];
        return (-1+.5*y1)*y2;
    }
}
