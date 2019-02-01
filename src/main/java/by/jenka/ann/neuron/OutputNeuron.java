package by.jenka.ann.neuron;

public class OutputNeuron extends Neuron
{
    private float error;

    public void calculateError(float expectedOutput)
    {
        error = expectedOutput - output;
    }
}
