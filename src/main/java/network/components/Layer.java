package network.components;

import network.components.neurons.Neuron;

import java.util.LinkedList;

/**
 * Created by michal on 28.09.2017.
 */
public class Layer {

    static int layerNo = 0;
    private LinkedList<Neuron> neurons = new LinkedList<Neuron>();
    private String layerName;

    public Layer(Class neuronClass, int numberOfNeurons) throws IllegalAccessException, InstantiationException {
        layerNo++;
        this.layerName = layerNo + "";
        for (int i = 0; i < numberOfNeurons; i++) {
            neurons.add((Neuron) neuronClass.newInstance());
        }
    }

    public Layer(String layerName) {
        layerNo++;
        this.layerName = layerNo + "";
    }

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }


    public LinkedList<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(LinkedList<Neuron> neurons) {
        this.neurons = neurons;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
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
