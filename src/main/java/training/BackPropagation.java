package training;

import costfunctions.CostFunction;
import data.DataRow;
import data.DataSet;
import network.components.Connection;
import network.components.Layer;
import network.components.Network;
import network.components.activationfunctions.ActivationFunction;
import network.components.neurons.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by michal on 29.09.2017.
 */
public class BackPropagation {

    private float error=0f;
    private static final float LEARNING_RATE = 0.12f;

    public void train(Network network, DataSet dataSet, ActivationFunction activationFunctions, CostFunction costFunction) {
        List<DataRow> dataRows = dataSet.getDataRows();
        for(int n=0;n<5000;n++) {
            for (DataRow dataRow : dataRows) {
                feedForward(network, activationFunctions, costFunction, dataRow);
                backPropagate(network, activationFunctions, costFunction, dataRow);
                System.out.println(getError());
            }
        }
    }

    public void feedForward(Network network, ActivationFunction activationFunctions, CostFunction costFunction, DataRow dataRow) {
        ListIterator<Layer> li = network.getLayers().listIterator();
        while (li.hasNext()) {
            if (!li.hasPrevious()) {
                /* First Layer */
                Layer layer = (Layer) li.next();
                LinkedList<Neuron> neurons = layer.getNeurons();

                int i = 0;
                for (Neuron neuron : neurons) {
                    if (neuron instanceof Bias) {
                        neuron.setInput(1f);
                        neuron.setOutput(1f);

                    }
                    if (neuron instanceof Neuron) {
                        neuron.setInput(dataRow.getVariables().get(i));
                        neuron.setOutput(dataRow.getVariables().get(i));
                        i++;
                    }
                }
            } else {
                Layer layer = (Layer) li.next();
                LinkedList<Neuron> neurons = layer.getNeurons();
                /* Hidden Layer */
                for (Neuron neuron : neurons) {
                    float inputSum=0f;
                    for (Connection connection : neuron.getToConnections()) {
                        inputSum+=connection.getWeight()*connection.getNeuron().getOutput();
                    }
                    neuron.setInput(inputSum);
                    neuron.setOutput(activationFunctions.getActivation(inputSum));

                }
                if (!li.hasNext()) {
                /* Output Layer */
                    int i = 0;
                    for (Neuron neuron : neurons) {
                        this.setError(dataRow.getResults().get(0)-neuron.getOutput());
                        i++;
                    }
                }
            }
        }
    }

    public void backPropagate(Network network, ActivationFunction aF, CostFunction cF, DataRow dataRow) {
        ListIterator<Layer> li = network.getLayers().listIterator(network.getLayers().size());
        while (li.hasPrevious()) {
            if (!li.hasNext()) {
                Layer layer = (Layer) li.previous();
                LinkedList<Neuron> neurons = layer.getNeurons();
                /* Output Layer */
                int i = 0;
                for (Neuron neuron : neurons) {
                    List<Connection> toConnections = neuron.getToConnections();
                    float delta = 0f;
                    delta = LEARNING_RATE *
                            aF.getDerivative(neuron.getInput())*
                            //derivative cost
                            cF.getDerivative(
                                    aF.getActivation(neuron.getOutput())
                                    ,dataRow.getResults().get(i));

                    neuron.setDelta(delta);
                    i++;
                }
            } else {
                /* Hidden Layer */
                Layer layer = (Layer) li.previous();
                LinkedList<Neuron> neurons = layer.getNeurons();
                int i = 0;
                for (Neuron neuron : neurons) {
                    List<Connection> toConnections = neuron.getFromConnections();
                    float weightedSum = 0f;
                    for (Connection toConnection : toConnections) {
                        weightedSum += toConnection.getWeight() * toConnection.getNeuron().getDelta();
                        //https://youtu.be/I2I5ztVfUSE?t=157
                    }
                    //https://youtu.be/I2I5ztVfUSE?t=319
                    float innerDelta=0f;
                    List<Connection> fromConnections = neuron.getToConnections();
                    for (Connection fromConnection : fromConnections) {
                        innerDelta+=fromConnection.getWeight()*fromConnection.getNeuron().getOutput();
                    }


                    float totalDelta=LEARNING_RATE *aF.getDerivative(innerDelta)*weightedSum;
                    neuron.setDelta(totalDelta);



                    for(Connection connection:neuron.getToConnections())
                    {
                        connection.setWeight(connection.getWeight()-connection.getNeuron().getDelta());
                    }

                    i++;
                }
                if (!li.hasPrevious()) {
                /* Input Layer */
                    for (Neuron neuron : neurons) {
                     //   neuron.setCost(cF.getCost(neuron.getActivationOutput(), dataRow.getResults().get(i)));
                    }
                } else {
                }
            }









        }
    }

    public float getError() {
        return error;
    }

    public void setError(float error) {
        this.error = error;
    }
}