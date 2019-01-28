package by.jenka.ann.neuron;

import by.jenka.ann.network.HyperParams;

public class InputNeuron extends Neuron
{

    @Override
    public void calculateDelta(float expectedOutput)
    {
        throw new UnsupportedOperationException("Calculate delta is not allowed for InputNeuron.");
    }

    @Override
    public void backwardPropagate(float expectedOutput, HyperParams hyperParams)
    {
        throw new UnsupportedOperationException("BackwardPropagateDelta is not allowed for InputNeuron.");
    }
}
