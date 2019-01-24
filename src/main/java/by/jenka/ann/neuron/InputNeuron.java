package by.jenka.ann.neuron;

public class InputNeuron extends Neuron
{
    public InputNeuron(float input)
    {
        this.input = input;
        this.output = input;
    }

    @Override
    public float getOutput()
    {
        return output;
    }
}
