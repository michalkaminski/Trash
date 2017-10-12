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
    private Neuron fromNeuron;
    private Neuron toNeuron;

    private float weight;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    public Neuron getFromNeuron() {
        return fromNeuron;
    }

    public void setFromNeuron(Neuron fromNeuron) {
        this.fromNeuron = fromNeuron;
    }

    public Neuron getToNeuron() {
        return toNeuron;
    }

    public void setToNeuron(Neuron toNeuron) {
        this.toNeuron = toNeuron;
    }

    @Override
    public String toString() {
        return "("+weight+")";
    }
}
