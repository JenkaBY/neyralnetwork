package by.jenka.ann.neuron;

public class OutputNeuron extends Neuron
{
    private float error;

    @Override
    public void calculateDelta(float error)
    {
//        error = (expectedOutput - output)
        delta = error * activationFunction.calculateDerivative(output);
    }

    public void calculateError(float expectedOutput)
    {
        error = expectedOutput - output;
    }
}
