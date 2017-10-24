package network.components.neurons;

import network.components.Connection;
import network.components.Layer;
import network.components.activationfunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by michal on 28.09.2017.
 */

public  class Neuron {

    public Neuron()
    {}
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

    public void connectToNeuron(Neuron toNeuron, float weight)
    {
        Connection connection=new Connection(this,toNeuron,weight);
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


    public float getOutput() {
        return output;
    }

    public void setOutput(float output) {
        this.output = output;
    }


    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }


    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getDcost_dpred() {
        return dcost_dpred;
    }

    public void setDcost_dpred(float dcost_dpred) {
        this.dcost_dpred = dcost_dpred;
    }

    public float getInput() {
        return input;
    }

    public void setInput(float input) {
        this.input = input;
    }

    @Override
    public String toString() {

        return this.getClass().getSimpleName()
                 ;
    }
}
