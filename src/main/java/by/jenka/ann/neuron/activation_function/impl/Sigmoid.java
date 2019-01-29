package by.jenka.ann.neuron.activation_function.impl;

import by.jenka.ann.neuron.activation_function.ActivationFunctionStrategy;

public class Sigmoid implements ActivationFunctionStrategy
{
    @Override
    public float execute(float input)
    {
        return (float) (1 / (1 + Math.exp(-input)));
    }

    @Override
    public float calculateDerivative(float output)
    {
        return (1 - output) * output;
    }
}
