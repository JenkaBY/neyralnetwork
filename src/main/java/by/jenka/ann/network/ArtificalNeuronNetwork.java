package by.jenka.ann.network;

import by.jenka.ann.layer.HiddenLayer;
import by.jenka.ann.layer.InputLayer;
import by.jenka.ann.layer.Layer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArtificalNeuronNetwork
{
    private List<Layer> layers;

    public void addLayer(Layer layer)
    {
        layer.setLevel(layers.size());
        layers.add(layer);
    }

    private List<InputLayer> getInputLayers()
    {
        return getStreamedLayers()
                .filter(layer -> layer instanceof InputLayer)
                .map(layer -> (InputLayer) layer)
                .collect(Collectors.toList());
    }

    private InputLayer getInputLayer()
    {
        return getInputLayers().get(0);
    }

    private List<HiddenLayer> getHiddenLayers()
    {
        return getStreamedLayers()
                .filter(layer -> layer instanceof HiddenLayer)
                .map(layer -> (HiddenLayer) layer)
                .collect(Collectors.toList());
    }

    private Stream<Layer> getStreamedLayers()
    {
        return layers.stream();
    }

    private void setInputsForHiddenLayer()
    {
        InputLayer inputLayer = getInputLayer();
        inputLayer.getNeurons().forEach(
                neuron -> {
                    if (inputLayer.getLevel() < layers.size() - 1) {
                        getNextLayer(inputLayer.getLevel()).getNeurons().forEach(
                                nextLayerNeuron -> nextLayerNeuron.setInput(neuron.getOutput())
                        );
                    }
                }
        );
    }

    private void setInputsForOtherLayers()
    {
        getStreamedLayers()
                .skip(1) //skip input layer
                .forEach(layer -> layer.getNeurons()
                        .forEach(
                                neuron -> {
                                    if (layer.getLevel() < layers.size() - 1) {
                                        getNextLayer(layer.getLevel()).getNeurons()
                                                .forEach(nextLayerNeuron -> nextLayerNeuron.setInput(neuron.getOutput()));
                                    }
                                }
                        )
                );

    }

    private Layer getNextLayer(int currentLevel)
    {
        return getStreamedLayers()
                .filter(layer -> layer.getLevel() == currentLevel + 1)
                .findFirst()
                .get();
    }
}
