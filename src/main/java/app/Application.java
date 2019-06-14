package app;

import costfunctions.CostFunction;
import costfunctions.CostQuadratic;
import data.DataRow;
import data.DataSet;
import network.components.Layer;
import network.components.Network;
import network.components.activationfunctions.ActivationFunction;
import network.components.activationfunctions.ActivationSigmoid;
import network.components.neurons.Bias;
import network.components.neurons.Neuron;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import training.BackPropagation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by michal on 29.09.2017.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
       // SpringApplication.run(Application.class, args);

        Network network=new Network();

        Layer inputLayer=new Layer(Neuron.class,2);
        Layer hiddenLayer=new Layer(Neuron.class,50);
        Layer outputLayer=new Layer(Neuron.class,1);
        Neuron b1 = new Bias();
        Neuron b2 = new Bias();



        network.addLayer(inputLayer);
        network.addLayer(hiddenLayer);
        network.addLayer(outputLayer);

        inputLayer.addNeuron(b1);
        hiddenLayer.addNeuron(b2);

        b1.connectToLayer(hiddenLayer);
        b2.connectToLayer(outputLayer);

        BackPropagation backPropagation=new BackPropagation();
        DataSet dataSet=new DataSet();
        DataRow dataRow1=new DataRow();
        DataRow dataRow2 =new DataRow();
        DataRow dataRow3=new DataRow();
        DataRow dataRow4 =new DataRow();


        dataRow1.setVariables(new ArrayList<Float>(Arrays.asList(0f, 1f)));
        dataRow1.setResults(new ArrayList<Float>(Arrays.asList(0f)));

        dataRow2.setVariables(new ArrayList<Float>(Arrays.asList(1f, 1f)));
        dataRow2.setResults(new ArrayList<Float>(Arrays.asList(1f)));

        dataRow3.setVariables(new ArrayList<Float>(Arrays.asList(1f, 0f)));
        dataRow3.setResults(new ArrayList<Float>(Arrays.asList(1f)));

        dataRow4.setVariables(new ArrayList<Float>(Arrays.asList(0f, 0f)));
        dataRow4.setResults(new ArrayList<Float>(Arrays.asList(0f)));

        dataSet.getDataRows().add(dataRow1);
        dataSet.getDataRows().add(dataRow2);
        dataSet.getDataRows().add(dataRow3);
        dataSet.getDataRows().add(dataRow4);


        System.out.println(network);

        ActivationFunction activationFunctions=new ActivationSigmoid();
        CostFunction costFunction= new CostQuadratic();
        backPropagation.train(network,dataSet,activationFunctions,costFunction,1000);
        backPropagation.test(network,dataSet,activationFunctions,costFunction);
System.out.println(network);
    }
}