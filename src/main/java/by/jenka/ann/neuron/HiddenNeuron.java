package by.jenka.ann.neuron;

public class HiddenNeuron extends Neuron
{
    @Override
    public void calculateDelta(float error)
    {
        delta = activationFunction.calculateDerivative(error);
    }

}
