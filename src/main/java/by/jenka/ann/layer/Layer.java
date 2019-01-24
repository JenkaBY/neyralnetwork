package by.jenka.ann.layer;

import by.jenka.ann.neuron.Neuron;

import java.util.Collection;
import java.util.List;

public abstract class Layer
{
    protected List<Neuron> neurons;
    protected int level;

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
}
