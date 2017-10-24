package training;

import costfunctions.CostFunction;
import data.DataRow;
import data.DataSet;
import mathvisualizations.leastsquares.LeastSquares;
import network.components.Connection;
import network.components.Layer;
import network.components.Network;
import network.components.activationfunctions.ActivationFunction;
import network.components.neurons.*;

import javax.swing.*;
import java.applet.Applet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by michal on 29.09.2017.
 */
public class BackPropagation {

    private float error = 0f;
    private static final float LEARNING_RATE = 0.35f;

    List<Float> xs=new ArrayList<Float>();
    List<Float> ys=new ArrayList<Float>();

    public void train(Network network, DataSet dataSet, ActivationFunction activationFunctions, CostFunction costFunction,int iterations) {
        List<DataRow> dataRows = dataSet.getDataRows();
        for (int n = 0; n < iterations; n++) {
            int i=1;
            for (DataRow dataRow : dataRows) {
                feedForward(network, activationFunctions, costFunction, dataRow);
                backPropagate(network, activationFunctions, costFunction, dataRow);
//                System.out.println(String.format(i+" Error: %.7f", getError()*100)+"%" );
                i++;
            }
            xs.add((float)n);
            ys.add(getError());


        }

        JFrame frame = new JFrame();
        frame.setSize(400, 300);

        final Applet applet = new LeastSquares();

        frame.getContentPane().add(applet);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                applet.stop();
                applet.destroy();
                System.exit(0);
            }
        });

        frame.setVisible(true);
        ((LeastSquares)applet).setxValues(xs);
        ((LeastSquares)applet).setyValues(ys);

        applet.init();
        applet.start();


        }


    public void test(Network network, DataSet dataSet, ActivationFunction activationFunctions, CostFunction costFunction) {
        List<DataRow> dataRows = dataSet.getDataRows();
            System.out.println("Testing results:");
            int i=1;
            for (DataRow dataRow : dataRows) {
                feedForward(network, activationFunctions, costFunction, dataRow);
                System.out.println(String.format(i+" Error: %.7f", getError()*100)+"%" );
                i++;
        }
    }
    public void feedForward(Network network, ActivationFunction aF, CostFunction cF, DataRow dataRow) {

        /* First Layer */
        Layer inputLayer=network.getLayers().getFirst();
        LinkedList<Neuron> neurons = inputLayer.getNeurons();
        int i = 0;
        for (Neuron neuron : neurons) {
            if (neuron instanceof Bias) {
                neuron.setInput(1f);
                neuron.setOutput(1f);
            }
            if (!(neuron instanceof Bias)) {
                neuron.setInput(dataRow.getVariables().get(i));
                neuron.setOutput(dataRow.getVariables().get(i));
                i++;
            }
        }

        for(int l=1;l<=network.getLayers().size()-1;l++)
        {
            Layer notFirstLayer=network.getLayers().get(l);
            LinkedList<Neuron> notFirstLayerNeurons = notFirstLayer.getNeurons();
                /* Not first layer */
            for (Neuron neuron : notFirstLayerNeurons) {
                float z = 0f;
                for (Connection connection : neuron.getConnections()) { //getToConnections
                    if (connection.getToNeuron().equals(neuron)) {
                        z += connection.getWeight() * connection.getFromNeuron().getOutput();
                    }
                }
                neuron.setInput(z);
                neuron.setOutput(aF.getActivation(z));
            }

            if(notFirstLayer.equals(network.getLayers().getLast())) {
            /* Output Layer */
                int j = 0;
                float totalCost=0f;
                for (Neuron neuron : notFirstLayerNeurons) {
                    float z = 0f;
                    for (Connection connection : neuron.getConnections()) { //getToConnections
                        if (connection.getToNeuron().equals(neuron)) {
                            z += connection.getWeight() * connection.getFromNeuron().getOutput();
                        }
                    }
                    neuron.setInput(z);
                    neuron.setOutput(aF.getActivation(z));

                    float cost = 0f;
                    float dCost = 0f;
                    cost = (((float) Math.pow(neuron.getOutput() - dataRow.getResults().get(j), 2)));
                    neuron.setCost(cost);
                    /* 0 jak zmieni sie koszt gdy zmieni sie waga laczaca z poprzedzajacym neuronem */
                    /* 1 tylko dla output layer */
                    //Jak zmienia sie wartosc kosztu w zaleznosci od zmiany outputu(prediction)
                    dCost = cF.getDerivative(neuron.getOutput(), dataRow.getResults().get(j));
                    neuron.setDcost_dpred(dCost);

                    totalCost+=cost;
                    j++;
                }
                //let dpred_dz = sigmoid(z) * (1-sigmoid(z));
                this.setError(totalCost);
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
                for (Neuron neuron : neurons) {

                    /* 2 jak zmieni sie output (prediction) neuronu ostatniej warstwy gdy zmieni sie suma inputow z poprzedzajacych neuronow */
                    float dpred_dz=aF.getDerivative(neuron.getInput());
                    for(Connection connection:neuron.getConnections())
                    {
                        if(connection.getToNeuron().equals(neuron))
                        {
                            /* 3 jak zmieni sie suma inputow z poprzedzajacych neuronow gdy zmieni sie konkretnie ta waga */
                            /* obliczajac pochodna otrzymujemy w rezultacie output z=w1*point1+w2*point2, pochodna jest po prostu point1 czyli*/
                            float dz_dw=connection.getFromNeuron().getOutput();
                            // mnożymy wszystkie skladniki ze soba 1,2,3 i otrzymujemy calkowity wplyw
                            float dcost_dw = neuron.getDcost_dpred() * dpred_dz * dz_dw;
                            connection.setWeight(connection.getWeight()- LEARNING_RATE* dcost_dw);
                        }
                    }
                }
            } else {
                /* Hidden Layer */
                Layer layer = (Layer) li.previous();
                LinkedList<Neuron> neurons = layer.getNeurons();
                for (Neuron neuron : neurons) {
                    float dpred_dz=aF.getDerivative(neuron.getInput());
                    float dcost=0f;
                    for(Connection connection:neuron.getConnections())
                    {
                        if(connection.getFromNeuron().equals(neuron))
                        {
                            dcost+=(connection.getToNeuron().getDcost_dpred()*connection.getWeight());
                        }
                    }
                    neuron.setDcost_dpred(dcost);
                    for(Connection connection:neuron.getConnections())
                    {
                        if(connection.getToNeuron().equals(neuron))
                        {
                            float dz_dw=connection.getFromNeuron().getOutput();
                            float dcost_dw = neuron.getDcost_dpred() * dpred_dz * dz_dw;
                            connection.setWeight(connection.getWeight()- LEARNING_RATE* dcost_dw);
                        }
                    }
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