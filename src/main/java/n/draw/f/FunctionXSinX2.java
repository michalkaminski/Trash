package n.draw.f;

/**
 * Created by michal on 23.05.2019.
 */
public class FunctionXSinX2 implements Function {
    @Override
    public double valueOf(double... arg) {
        double x = arg[0];
        return x*Math.sin(Math.pow(x,2))+1;
    }

    @Override
    public double derivative(double... arg) {
        double x = arg[0];
        return Math.sin(Math.pow(x,2))+2*Math.pow(x,2)*Math.cos(Math.pow(x,2));
    }
}
