package by.jenka.ann.neuron;

import by.jenka.ann.network.HyperParams;

public class HiddenNeuron extends Neuron {
    @Override
    public void calculateDelta(float expectedOutput, HyperParams hyperParams) {
        delta = activationFunction.calculateDerivative(expectedOutput);
    }
}
