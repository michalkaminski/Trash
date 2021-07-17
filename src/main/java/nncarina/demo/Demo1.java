package nncarina.demo;

import nncarina.components.costfunctions.CostFunction;
import nncarina.components.costfunctions.CostQuadratic;
import nncarina.components.data.DataRow;
import nncarina.components.data.DataSet;
import lombok.extern.slf4j.Slf4j;
import nncarina.components.networkelements.Layer;
import nncarina.components.networkelements.Network;
import nncarina.components.activationfunctions.ActivationFunction;
import nncarina.components.activationfunctions.ActivationSigmoid;
import nncarina.components.neurons.Bias;
import nncarina.components.neurons.Neuron;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import nncarina.components.training.BackPropagation;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.asList;

@Slf4j
@SpringBootApplication
public class Demo1 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // SpringApplication.run(Application.class, args);

        Network network = new Network();

        Layer inputLayer = new Layer(Neuron.class, 2);
        Layer hiddenLayer = new Layer(Neuron.class, 1);


        Layer outputLayer = new Layer(Neuron.class, 1);
        Neuron b1 = new Bias("b1");
        Neuron b2 = new Bias("b2");


        network.addLayer(inputLayer);
        network.addLayer(hiddenLayer);

        network.addLayer(outputLayer);

        inputLayer.addNeuron(b1);
        hiddenLayer.addNeuron(b2);

        b1.connectToLayer(hiddenLayer);
        b2.connectToLayer(outputLayer);

        BackPropagation backPropagation = new BackPropagation();
        DataSet dataSet = new DataSet();
        DataRow dataRow1 = new DataRow();
        DataRow dataRow2 = new DataRow();
        DataRow dataRow3 = new DataRow();
        DataRow dataRow4 = new DataRow();


        dataRow1.setVariables(new ArrayList<>(asList(0f, 1f, 0f)));
        dataRow1.setResults(new ArrayList<>(asList(0f)));

        dataRow2.setVariables(new ArrayList<>(asList(1f, 1f, 0f)));
        dataRow2.setResults(new ArrayList<>(asList(1f)));

        dataRow3.setVariables(new ArrayList<>(asList(1f, 0f, 0f)));
        dataRow3.setResults(new ArrayList<>(asList(1f)));

        dataRow4.setVariables(new ArrayList<>(asList(0f, 0f, 0f)));
        dataRow4.setResults(new ArrayList<>(asList(0f)));

        dataSet.getDataRows().add(dataRow1);
        dataSet.getDataRows().add(dataRow2);
        dataSet.getDataRows().add(dataRow3);
        dataSet.getDataRows().add(dataRow4);


        log.info(network.toString());

        ActivationFunction activationFunctions = new ActivationSigmoid();
        CostFunction costFunction = new CostQuadratic();
        backPropagation.train(network, dataSet, activationFunctions, costFunction, 500);
//        backPropagation.test(network, dataSet, activationFunctions, costFunction);
        log.info(network.toString());
    }


}