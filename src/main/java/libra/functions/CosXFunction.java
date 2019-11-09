package libra.functions;

/**
 * Created by michal on 23.05.2019.
 */
public class CosXFunction implements IFunction {
    @Override
    public double valueOf(double... arg) {
        double x = arg[0];
        return x;
    }

    @Override
    public double derivative(double... arg) {
        double x = arg[0];
        double y = arg[1];

        return -x-0.25*y;
    }
}
