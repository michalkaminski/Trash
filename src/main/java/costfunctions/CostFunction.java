package costfunctions;

/**
 * Created by michal on 29.09.2017.
 */
public interface CostFunction {

    public float getCost (float pred, float target);
    public float getDerivative (float pred, float target);
    }
