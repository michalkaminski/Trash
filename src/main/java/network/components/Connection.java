package network.components;

import network.components.neurons.Neuron;

import java.util.Random;

/**
 * Created by michal on 29.09.2017.
 */


public class Connection {

public Connection()
{
    Random rand = new Random();
    this.weight = rand.nextFloat();
}
    private Neuron neuron;
    private float weight;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    public Neuron getNeuron() {
        return neuron;
    }

    public void setNeuron(Neuron neuron) {
        this.neuron = neuron;
    }

    @Override
    public String toString() {
        return "("+weight+")";
    }
}
