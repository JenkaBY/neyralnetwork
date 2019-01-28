package by.jenka.ann.neuron;

public class HiddenNeuron extends Neuron
{
    @Override
    public void calculateDelta(float expectedOutput)
    {
        delta = activationFunction.calculateDerivative(expectedOutput);
    }
}
