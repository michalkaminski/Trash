package network.components;

import network.components.Layer;
import network.components.neurons.Neuron;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * Created by michal on 29.09.2017.
 */


public class Network {

private LinkedList<Layer> layers=new LinkedList<Layer>();

    public Network()
    {

    }

    public void addLayer(Layer layer)
    {
        if(!layers.isEmpty())
        {
            Layer lastLayer = layers.getLast();
            connectLayers(lastLayer,layer);
        }
            layers.add(layer);

    }

    private void connectLayers(Layer lastLayer, Layer newLayer) {
        LinkedList<Neuron> lastNeurons = lastLayer.getNeurons();
        LinkedList<Neuron> newNeurons = newLayer.getNeurons();
        for(Neuron lastNeuron:lastNeurons)
        {
            for(Neuron newNeuron:newNeurons)
            {
                lastNeuron.connectToNeuron(newNeuron);
            }
        }
    }


    public void removeLayer(Layer layer)
    {
        layers.remove(layer);
    }

    public LinkedList<Layer> getLayers() {
        return layers;
    }

    public void setLayers(LinkedList<Layer> layers) {
        this.layers = layers;
    }




    @Override
    public String toString() {
        return "Network{\n" +
                "layers=" + layers +
                "\n}";
    }
}
