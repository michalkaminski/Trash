package network.components.activationfunctions;

import network.components.neurons.Neuron;

/**
 * Created by michal on 28.09.2017.
 */
public interface ActivationFunction {

    public float getActivation(float sum);
    public float getDerivative(float sum);


}
