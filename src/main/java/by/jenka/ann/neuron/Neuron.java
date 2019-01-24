package by.jenka.ann.neuron;

public abstract class Neuron
{
    protected float input;
    protected float output;

    public float getInput()
    {
        return input;
    }

    public void setInput(float input)
    {
        this.input = input;
    }

    public abstract float getOutput();

}
