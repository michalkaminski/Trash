package nncarina.components.networkelements;

import lombok.Getter;
import lombok.Setter;
import nncarina.components.neurons.Neuron;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michal on 29.09.2017.
 */

@Getter
@Setter
public class Network {

private List<Layer> layers=new LinkedList<Layer>();

    public Network() {}


    public void addLayer(Layer layer)
    {
        if(!layers.isEmpty())
        {
            Layer lastLayer = (Layer) ((LinkedList)layers).getLast();
            connectLayers(lastLayer,layer);
        //TODO Lastlayer??/ only two layer possible?

        }
            layers.add(layer);
    }

    private void connectLayers(Layer lastLayer, Layer newLayer) {
        List<Neuron> lastNeurons = lastLayer.getNeurons();
        List<Neuron> newNeurons = newLayer.getNeurons();
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



    @Override
    public String toString() {
        return "Network{\n" +
                "layers=" + layers +
                "\n}";
    }
}
