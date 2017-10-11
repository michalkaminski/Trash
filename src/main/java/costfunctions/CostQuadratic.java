package costfunctions;

/**
 * Created by michal on 29.09.2017.
 */
public class CostQuadratic implements CostFunction {
    @Override
    public float getCost(float pred, float target) {
        return (float) Math.pow(pred - target,2);
    }
    public float getDerivative(float pred,float target)
    {
        return 2*(pred-target);
    }
}
