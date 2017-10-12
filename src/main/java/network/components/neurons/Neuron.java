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

    private List<Connection> connections=new ArrayList<Connection>();

    public void connectToNeuron(Neuron toNeuron)
    {
        Connection connection=new Connection();
        connection.setFromNeuron(this);
        connection.setToNeuron(toNeuron);
        this.getConnections().add(connection);
        toNeuron.getConnections().add(connection);
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
                ": conn=" + connections.size() +
                ";o=" + output +"]"+
        ": conn=" + connections
                 ;
    }
}
