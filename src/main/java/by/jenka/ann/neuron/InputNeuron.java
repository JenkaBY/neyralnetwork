package by.jenka.ann.neuron;

import by.jenka.ann.network.HyperParams;

public class InputNeuron extends Neuron
{

    @Override
    public void calculateDelta(float expectedOutput, HyperParams hyperParams) {
        throw new UnsupportedOperationException("Calculate delta is not allowed for InputNeuron.");
    }
}
