package network.components;

import lombok.Getter;
import lombok.Setter;
import network.components.neurons.Neuron;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Layer {

    static int layerNo = 0;

    private List<Neuron> neurons = new ArrayList<Neuron>();
    private String layerName;

    public Layer(Class neuronClass, int numberOfNeurons) throws IllegalAccessException, InstantiationException {
        layerNo++;
        this.layerName = Integer.toString(layerNo);
        for (int i = 1; i <= numberOfNeurons; i++) {
            Neuron neuron=(Neuron) neuronClass.newInstance();
            neuron.setName(Integer.toString(i));
            neurons.add(neuron);
        }
    }


    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }



    @Override
    public String toString() {

        String ls = layerName + ":";
        for (Neuron neuron : neurons) {
            ls = ls + " (" + neuron + ")";
        }
        return '\n' + ls + '\n';
    }
}
