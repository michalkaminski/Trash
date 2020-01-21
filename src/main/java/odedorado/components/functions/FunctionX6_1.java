package odedorado.components.functions;

/**
 * Created by michal on 23.05.2019.
 */
public class FunctionX6_1 implements IFunction {
    @Override
    public double valueOf(double... arg) {
        double x = arg[0];
        return 2*Math.pow(x,6)+3*Math.pow(x,5)+Math.pow(x,3)-1;
    }

    @Override
    public double derivative(double... arg) {
        double x = arg[0];
        return 6*2*Math.pow(x,5)+5*3*Math.pow(x,4)+3*Math.pow(x,2);
    }
}
