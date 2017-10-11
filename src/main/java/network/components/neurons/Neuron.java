package network.components.neurons;

import network.components.Connection;
import network.components.activationfunctions.ActivationFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 28.09.2017.
 */

public  class Neuron {

    public Neuron(){}
    private float input;
    private float output;

    private float error;
    private float delta; //gradient

    private List<Connection> toConnections=new ArrayList<Connection>();
    private List<Connection> fromConnections=new ArrayList<Connection>();





    public void connectToNeuron(Neuron toNeuron)
    {
        Connection fromConnection=new Connection();
        fromConnection.setNeuron(toNeuron);
        this.getFromConnections().add(fromConnection);

        Connection toConnection=new Connection();
        toConnection.setNeuron(this);
        toNeuron.getToConnections().add(toConnection);
    }



    public float getOutput() {
        return output;
    }

    public void setOutput(float output) {
        this.output = output;
    }

    public List<Connection> getToConnections() {
        return toConnections;
    }

    public void setToConnections(List<Connection> toConnections) {
        this.toConnections = toConnections;
    }

    public List<Connection> getFromConnections() {
        return fromConnections;
    }

    public void setFromConnections(List<Connection> fromConnections) {
        this.fromConnections = fromConnections;
    }





    public float getError() {
        return error;
    }

    public void setError(float error) {
        this.error = error;
    }


    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }


    public float getInput() {
        return input;
    }

    public void setInput(float input) {
        this.input = input;
    }

    @Override
    public String toString() {

        return "["+this.getClass().getSimpleName() +
                ": from=" + fromConnections.size() +
                ";to=" + toConnections.size() +
                ";o=" + output +"]"+
        ": from=" + fromConnections +
                ";to=" + toConnections
                ;
    }
}
