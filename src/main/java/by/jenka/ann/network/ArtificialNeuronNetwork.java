package by.jenka.ann.network;

import by.jenka.ann.layer.HiddenLayer;
import by.jenka.ann.layer.InputLayer;
import by.jenka.ann.layer.Layer;
import by.jenka.ann.layer.OutputLayer;
import by.jenka.ann.neuron.Neuron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtificialNeuronNetwork {
    private static final Logger logger = LoggerFactory.getLogger(ArtificialNeuronNetwork.class);
    private List<Layer> layers;

    public ArtificialNeuronNetwork() {
        layers = new ArrayList<>();
    }

    public void addLayer(Layer layer) {
        layer.setLevel(layers.size() + 1);
        layers.add(layer);
    }

    public void start(float[] inputData, float[] expected) {
        final float[] result = calculate(inputData);
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("Expected: " + Arrays.toString(expected));

    }

    public float[] calculate(float[] inputData) {
        final List<Neuron> inputNeurons = getInputLayer().getNeurons();
        assert inputData.length == inputNeurons.size();

        for (int i = 0; i < inputData.length; i++) {
            inputNeurons.get(i).setInput(inputData[i]);
        }

        log("set up input");
        setConnectedNeuronsOnLayers();
        getStreamedLayers().forEach(layer -> {
            layer.getNeurons().forEach(Neuron::updateInput);
        });

        log("update input");
        final List<Float> result = getOutputLayer().getNeurons().stream()
                .map(Neuron::getOutput)
                .collect(Collectors.toList());

        log("get output");
        float[] output = new float[result.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = result.get(i);
        }
        ;
        return output;
    }

    private void log(String stage) {
        logger.debug(stage.toUpperCase());
        layers.forEach(layer -> logger.debug(layer.toString()));
    }

    private void setConnectedNeuronsOnLayers() {
        getStreamedLayersWithoutOutputLayer().forEach(
                layer -> {
                    layer.getNeurons().forEach(
                            neuron -> {
                                getNextLayer(layer.getLevel()).setConnectedNeuron(neuron);
                            }
                    );
                }
        );
    }

    private Stream<Layer> getStreamedLayersWithoutOutputLayer() {
        return getStreamedLayers().limit(layers.size() - 1);
    }

    private List<InputLayer> getInputLayers() {
        return getStreamedLayers()
                .filter(layer -> layer instanceof InputLayer)
                .map(layer -> (InputLayer) layer)
                .collect(Collectors.toList());
    }

    private InputLayer getInputLayer() {
        return getInputLayers().get(0);
    }

    private List<HiddenLayer> getHiddenLayers() {
        return getStreamedLayers()
                .filter(layer -> layer instanceof HiddenLayer)
                .map(layer -> (HiddenLayer) layer)
                .collect(Collectors.toList());
    }

    private OutputLayer getOutputLayer() {
        return (OutputLayer) layers.get(layers.size() - 1);
    }

    private Stream<Layer> getStreamedLayers() {
        return layers.stream();
    }

    private Layer getNextLayer(int currentLevel) {
        return getStreamedLayers()
                .filter(layer -> layer.getLevel() == currentLevel + 1)
                .findFirst()
                .get();
    }
}
