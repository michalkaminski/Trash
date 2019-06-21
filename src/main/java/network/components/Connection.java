package network.components;

import lombok.Getter;
import lombok.Setter;
import network.components.neurons.Neuron;

import java.util.Random;

/**
 * Created by michal on 29.09.2017.
 */

@Getter
@Setter
public class Connection {

    float minX = -1f;
    float maxX = 1f;

public Connection()
{
    Random rand = new Random();
    this.weight = rand.nextFloat()* (maxX - minX) + minX;;
}
    private Neuron fromNeuron;
    private Neuron toNeuron;

    private float weight;
    private float newWeight;

    @Override
    public String toString() {
        return "("+weight+")";
    }



}
