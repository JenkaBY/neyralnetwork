package by.jenka.ann.neuron;

import by.jenka.ann.collection.SafeArrayList;
import by.jenka.ann.network.HyperParams;
import by.jenka.ann.neuron.activation_function.ActivationFunctionStrategy;
import by.jenka.ann.neuron.activation_function.impl.NoopActivationFunction;
import by.jenka.ann.neuron.randomized_wieght.RandomWeightStrategy;
import by.jenka.ann.neuron.randomized_wieght.impl.NoopRandom;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron implements Connectable {
    protected float input;
    protected float output;
    protected List<Float> weights;
    protected List<Neuron> connectedNeurons;

    protected ActivationFunctionStrategy activationFunction;
    private RandomWeightStrategy randomWeightStrategy;
    private boolean wasOutputCalculated;
    protected float delta;
    protected List<Float> grads;
    protected List<Float> historyWeightDeltas;


    public abstract void calculateDelta(float error);

    public Neuron() {
        initDefaultActivationFunction();
        initDefaultRandomizedWeightStrategy();
        weights = new ArrayList<>();
        connectedNeurons = new ArrayList<>();

        grads = new SafeArrayList<>();
        historyWeightDeltas = new SafeArrayList<>();
    }

    public float getInput() {
        return input;
    }

    public void setInput(float input) {
        this.input = input;
    }

    public float getOutput() {
        if (!wasOutputCalculated) {
            updateInput();
            output = activationFunction.execute(input);
            wasOutputCalculated = true;
        }
        return output;
    }


    public void backwardPropagate(float error, HyperParams hyperParams)
    {
        calculateDelta(error);
        for (int i = 0; i < connectedNeurons.size(); i++) {
            Neuron connectedNeuron = connectedNeurons.get(i);
            float weightConnectedNeuron = weights.get(i);
            float deltaConnectedNeuron = activationFunction.calculateDerivative(connectedNeuron.output) * (weightConnectedNeuron * delta);
            connectedNeuron.setDelta(deltaConnectedNeuron);
            float grad = connectedNeuron.getOutput() * delta;

            grads.set(i, grad);
            float oldWeightDelta = historyWeightDeltas.get(i) != null ? historyWeightDeltas.get(i) : 0f;
            float newWeightDelta = hyperParams.getSpeed() * grad + hyperParams.getMoment() * oldWeightDelta;
            historyWeightDeltas.set(i, newWeightDelta);
            weights.set(i, weights.get(i) + newWeightDelta);
        }
    }

    public void updateInput() {
        for (int i = 0; i < connectedNeurons.size(); i++) {
            input += connectedNeurons.get(i).getOutput() * weights.get(i);
        }
    }

    @Override
    public void connectWith(Neuron neuron) {
        connectedNeurons.add(neuron);
        weights.add(
                randomWeightStrategy.execute(
                        new Object[]{connectedNeurons.size()}
                ));
    }

    public void setActivationFunction(ActivationFunctionStrategy activationFunction) {
        this.activationFunction = activationFunction;
    }

    public void setRandomizedWeightStrategy(RandomWeightStrategy randomizedWeight) {
        this.randomWeightStrategy = randomizedWeight;
    }

    private void initDefaultActivationFunction() {
        this.activationFunction = new NoopActivationFunction();
    }

    private void initDefaultRandomizedWeightStrategy() {
        this.randomWeightStrategy = new NoopRandom();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!weights.isEmpty()) {
            for (int i = 0; i < weights.size(); i++) {
                Float weight = weights.get(i);
                sb.append("W").append(i).append(weight).append(",");
            }
        }
        return "N(" + hashCode() + ")" + "input: " + input + ", output: " + output + ", weights: " + sb.toString();
    }

    protected void setDelta(float delta) {
        this.input = delta;
    }
}
