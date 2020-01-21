package nncarina.components.activationfunctions;

/**
 * Created by michal on 29.09.2017.
 */
public class ActivationSigmoid implements ActivationFunction {

    @Override
    public float getActivation(float sum) {
        return getSigmoid(sum);
    }

    private float getSigmoid(float sum) {
        return (float) (1 / (1 + Math.exp(-sum)));
    }

    @Override
    public float getDerivative(float sum) {
        return getSigmoid(sum)*(1-getSigmoid(sum));
    }


}
