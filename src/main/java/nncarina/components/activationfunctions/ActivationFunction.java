package nncarina.components.activationfunctions;

/**
 * Created by michal on 28.09.2017.
 */
public interface ActivationFunction {

    public float getActivation(float sum);
    public float getDerivative(float sum);


}
