package by.jenka.ann.layer;

import by.jenka.ann.neuron.Neuron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Layer
{
    protected List<Neuron> neurons;
    protected int level;

    public Layer() {
        neurons = new ArrayList<>();
    }

    public List<Neuron> getNeurons()
    {
        return neurons;
    }

    public void addNueron(Neuron neuron)
    {
        neurons.add(neuron);
    }

    public void addNeurons(Collection<Neuron> neurons)
    {
        this.neurons.addAll(neurons);
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public void setConnectedNeuron(Neuron neuron) {
        neurons.forEach(layerNeuron -> layerNeuron.connectWith(neuron));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " level " + level + System.lineSeparator()
                + "\t" + getNeurons().stream()
                .map(Neuron::toString)
                .collect(Collectors.joining(System.lineSeparator() + "\t"));
    }
}
