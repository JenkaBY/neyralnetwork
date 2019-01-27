package by.jenka.ann;

import by.jenka.ann.layer.HiddenLayer;
import by.jenka.ann.layer.InputLayer;
import by.jenka.ann.layer.Layer;
import by.jenka.ann.layer.OutputLayer;
import by.jenka.ann.network.ArtificialNeuronNetwork;
import by.jenka.ann.network.HyperParams;
import by.jenka.ann.neuron.HiddenNeuron;
import by.jenka.ann.neuron.InputNeuron;
import by.jenka.ann.neuron.Neuron;
import by.jenka.ann.neuron.OutputNeuron;
import by.jenka.ann.neuron.activation_function.ActivationFunctionStrategy;
import by.jenka.ann.neuron.activation_function.impl.Sigmoid;
import by.jenka.ann.neuron.randomized_wieght.RandomWeightStrategy;
import by.jenka.ann.neuron.randomized_wieght.impl.SimpleRandom;
import by.jenka.ann.training.TrainingSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<TrainingSet> trainingSets = Arrays.asList(
                new TrainingSet(new float[]{0, 0}, new float[]{0}),
                new TrainingSet(new float[]{0, 1}, new float[]{1}),
                new TrainingSet(new float[]{1, 0}, new float[]{1}),
                new TrainingSet(new float[]{1, 1}, new float[]{1})
        );

        ActivationFunctionStrategy activationFunction = new Sigmoid();
        RandomWeightStrategy randomWeightStrategy = new SimpleRandom();
        HyperParams hyperParams = new HyperParams(0.5f, 0.3f);
        new ArrayList<String>().add(0, "213");
        Neuron inputNeuron1 = new InputNeuron();
        Neuron inputNeuron2 = new InputNeuron();
        Layer inputLayer = new InputLayer();
        inputLayer.addNeurons(Arrays.asList(
                inputNeuron1,
                inputNeuron2
        ));

        Neuron hiddenNeuron1 = new HiddenNeuron();
        hiddenNeuron1.setActivationFunction(activationFunction);
        hiddenNeuron1.setRandomizedWeightStrategy(randomWeightStrategy);

        Neuron hiddenNeuron2 = new HiddenNeuron();
        hiddenNeuron2.setActivationFunction(activationFunction);
        hiddenNeuron2.setRandomizedWeightStrategy(randomWeightStrategy);

        Neuron hiddenNeuron3 = new HiddenNeuron();
        hiddenNeuron3.setActivationFunction(activationFunction);
        hiddenNeuron3.setRandomizedWeightStrategy(randomWeightStrategy);

        Layer hiddenLayer = new HiddenLayer();
        hiddenLayer.addNeurons(Arrays.asList(
                hiddenNeuron1,
                hiddenNeuron2,
                hiddenNeuron3
        ));

        Neuron outputNeuron = new OutputNeuron();
        outputNeuron.setActivationFunction(activationFunction);
        outputNeuron.setRandomizedWeightStrategy(randomWeightStrategy);

        Layer outputLayer = new OutputLayer();
        outputLayer.addNeurons(Arrays.asList(
                outputNeuron)
        );

        ArtificialNeuronNetwork ann = new ArtificialNeuronNetwork();
        ann.addLayer(inputLayer);
        ann.addLayer(hiddenLayer);
        ann.addLayer(outputLayer);

        ann.start(trainingSets.get(1).getInput(), trainingSets.get(1).getExpectedResult());
    }
}
