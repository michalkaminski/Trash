package nncarina.components.neurons;

import lombok.Getter;
import lombok.Setter;
import nncarina.components.Connection;
import nncarina.components.Layer;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public  class Neuron {

    public Neuron() {}
    public Neuron(String name){
    this.name=name;
    }
    private String name;
    private float input;
    private float output;

    private float cost;
    private float dcost_dpred;


    private List<Connection> connections=new ArrayList<Connection>();

    public void connectToNeuron(Neuron toNeuron)
    {
        Connection connection=new Connection();
        connection.setFromNeuron(this);
        connection.setToNeuron(toNeuron);

        this.getConnections().add(connection);
        toNeuron.getConnections().add(connection);
    }


    public void connectToLayer(Layer layer)
    {
        for(Neuron neuron:layer.getNeurons())
        {
            this.connectToNeuron(neuron);
        }
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName()+":"+name;
    }
}
