package nncarina.components.training;

import nncarina.components.costfunctions.CostFunction;
import nncarina.components.data.DataRow;
import nncarina.components.data.DataSet;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import odes.demos.StdDraw;
import nncarina.components.networkelements.Connection;
import nncarina.components.networkelements.Layer;
import nncarina.components.networkelements.Network;
import nncarina.components.activationfunctions.ActivationFunction;
import nncarina.components.neurons.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

@Slf4j
@Getter
@Setter
public class BackPropagation {

    double SCALE = 0.40d;

    private static final float LEARNING_RATE = 0.35f;

    List<Float> xs = new ArrayList<>();
    List<Float> ys = new ArrayList<>();

    public BackPropagation() {
        StdDraw.setXscale(-0.1, SCALE);
        StdDraw.setYscale(-0.3, SCALE);
        StdDraw.setPenRadius(.003);
        StdDraw.enableDoubleBuffering();
    }

    public void train(Network network, DataSet dataSet, ActivationFunction activationFunctions, CostFunction costFunction, int iterations) {
        float previousTotalCost = 0;
        float totalCost;
        xs = new ArrayList<>();
        ys = new ArrayList<>();

        List<DataRow> dataRows = dataSet.getDataRows();
        for (int i = 0; i < iterations; i++) {
            for (DataRow dataRow : dataRows) {
                totalCost = feedForward(network, activationFunctions, costFunction, dataRow);
                log.info(i + ":" + totalCost);
                backPropagate(network, activationFunctions);

                visualize(i, previousTotalCost, totalCost);
                previousTotalCost = totalCost;

            }
            xs.add((float) i);
        }
    }

    public void test(Network network, DataSet dataSet, ActivationFunction activationFunctions, CostFunction costFunction) {
        log.info("Testing results:");

        for (DataRow dataRow : dataSet.getDataRows()) {
            feedForward(network, activationFunctions, costFunction, dataRow);
        }
    }

    public float feedForward(Network network, ActivationFunction aF, CostFunction cF, DataRow dataRow) throws RuntimeException {
        float totalCost = 0f;

        for (Layer layer : network.getLayers()) {
            int i = 0;
            for (Neuron neuron : layer.getNeurons()) {
                float cost = 0f;
                float dCost = 0f;

                if (layer.equals(((LinkedList) network.getLayers()).getFirst())) {
                    /* Input Layer */
                    if (dataRow.getVariables().size() != layer.getNeurons().size()) {
                        throw new RuntimeException("Invalid DataRow size." + dataRow.getVariables().size() + " It must be the size of input layer " + layer.getNeurons().size());
                    }

                    if (neuron instanceof Bias) {
                        neuron.setInput(1f);
                        neuron.setOutput(1f);
                    } else {
                        neuron.setInput(dataRow.getVariables().get(i));
                        neuron.setOutput(dataRow.getVariables().get(i));
                    }
                }
                if (!layer.equals(((LinkedList) network.getLayers()).getFirst())) {
                    //for hidden and output
                    float z = 0f;

                    for (Connection connection : neuron.getConnections()) {
                        if (connection.getToNeuron().equals(neuron)) {
                            //      let z = w1 * length + w2 * width + b;
                            z += connection.getWeight() * connection.getFromNeuron().getOutput();
                        }
                    }
                    neuron.setInput(z);
                    neuron.setOutput(aF.getActivation(z));
                    // '
                }
                if (layer.equals(((LinkedList) (network.getLayers())).getLast())) {
                    /* Output Layer */
                    if (dataRow.getResults().size() != layer.getNeurons().size()) {
                        throw new RuntimeException("Invalid DataRow results size." + dataRow.getResults().size() + " It must be the size of output layer " + layer.getNeurons().size());
                    }

                    cost = cF.getCost(neuron.getOutput(), dataRow.getResults().get(i));
                    neuron.setCost(cost);
                    //* 0 jak zmieni sie koszt gdy zmieni sie waga laczaca z poprzedzajacym neuronem */
                    /* 1 tylko dla output layer */
                    //Jak zmienia sie wartosc kosztu w zaleznosci od zmiany outputu(prediction)
                    dCost = cF.getDerivative(neuron.getOutput(), dataRow.getResults().get(i));
                    neuron.setDcost_dpred(dCost);
                    totalCost += cost;
                }
            }

            i++;
        }
        log.info("total cost:[{}]", Float.toString(totalCost));
        return totalCost;
    }

    public void backPropagate(Network network, ActivationFunction aF) {

        ListIterator<Layer> layers = network.getLayers().listIterator(network.getLayers().size());

        while (layers.hasPrevious()) {
            if (!layers.hasNext()) {
                /* Output Layer */
                for (Neuron neuron : layers.previous().getNeurons()) {/* 2 jak zmieni sie output (prediction) neuronu ostatniej warstwy gdy zmieni sie suma inputow z poprzedzajacych neuronow */
                    float dpred_dz = aF.getDerivative(neuron.getInput());
                    updateNewWeights(neuron, dpred_dz);
                }


            } else {
                /* Hidden Layer */
                for (Neuron neuron : layers.previous().getNeurons()) {

                    float dpred_dz = aF.getDerivative(neuron.getInput());
                    float dcost = 0f;

                    for (Connection connection : neuron.getConnections()) {
                        if (connection.getFromNeuron().equals(neuron)) {
                            dcost += (connection.getToNeuron().getDcost_dpred() * connection.getWeight());
                        }
                    }
                    neuron.setDcost_dpred(dcost);
                    updateNewWeights(neuron, dpred_dz);
                }
            }

//            let z = w1 * length + w2 * width + b;
//            let pred = sigmoid(z);
//            let cost = (pred - target) ** 2;
//            let dcost_dpred = 2 * (pred - target);
//            let dpred_dz = sigmoid(z) * (1-sigmoid(z));
//            let dz_dw1 = length;
//            let dz_dw2 = width;
//            let dz_db = 1;
//    ???        let dcost_dw1 = dcost_dpred * dpred_dz * dz_dw1;
//            let dcost_dw2 = dcost_dpred * dpred_dz * dz_dw2;
//            let dcost_db =  dcost_dpred * dpred_dz * dz_db;
//            w1 -= learning_rate * dcost_dw1;
//            w2 -= learning_rate * dcost_dw2;
//            b -= learning_rate * dcost_db;
        }
        updateWeights(network);

    }


    private void updateNewWeights(Neuron neuron, float dpred_dz) {
        for (Connection connection : neuron.getConnections()) {
            if (connection.getToNeuron().equals(neuron)) {
                /* 3 jak zmieni sie suma inputow z poprzedzajacych neuronow gdy zmieni sie konkretnie ta waga */
                /* obliczajac pochodna otrzymujemy w rezultacie output z=w1*point1+w2*point2, pochodna jest po prostu point1 czyli*/
                float dz_dw = connection.getFromNeuron().getOutput();
                // mnożymy wszystkie skladniki ze soba 1,2,3 i otrzymujemy calkowity wplyw
                float dcost_dw = neuron.getDcost_dpred() * dpred_dz * dz_dw;
                connection.setNewWeight(connection.getWeight() - LEARNING_RATE * dcost_dw);
            }
        }
    }

    private void updateWeights(Network network) {
        for (Layer layer : network.getLayers())
            for (Neuron neuron : layer.getNeurons()) {/* 2 jak zmieni sie output (prediction) neuronu ostatniej warstwy gdy zmieni sie suma inputow z poprzedzajacych neuronow */
                for (Connection connection : neuron.getConnections()) {
                    connection.setWeight(connection.getNewWeight());
                }
            }
    }


    static void visualize(int i, float previousTotalCost, float cost) {
        // StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.line(-1000, 0, 1000, 0);
        StdDraw.line(0, -1000, 0, 1000);

        StdDraw.setPenColor(StdDraw.RED);

        StdDraw.point(cost, cost - previousTotalCost);
        StdDraw.show();
    }
}