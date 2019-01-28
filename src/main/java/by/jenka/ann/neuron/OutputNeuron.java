package by.jenka.ann.neuron;

public class OutputNeuron extends Neuron
{
    @Override
    public void calculateDelta(float expectedOutput)
    {
        delta = (expectedOutput - output) * activationFunction.calculateDerivative(output);
    }
}
