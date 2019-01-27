package by.jenka.ann.neuron;

import by.jenka.ann.network.HyperParams;

public class OutputNeuron extends Neuron {
    @Override
    public void calculateDelta(float expectedOutput, HyperParams hyperParams) {
        delta = (expectedOutput - output) * activationFunction.calculateDerivative(output);
        backwardPropagateDelta(hyperParams);
    }

    protected void backwardPropagateDelta(HyperParams hyperParams) {
        for (int i = 0; i < connectedNeurons.size(); i++) {
            Neuron connectedNeuron = connectedNeurons.get(i);
            float weightConnectedNeuron = weights.get(i);
            float deltaConnectedNeuron = activationFunction.calculateDerivative(connectedNeuron.output) * (weightConnectedNeuron * delta);
            connectedNeuron.setDelta(deltaConnectedNeuron);
            float grad = connectedNeuron.getOutput() * delta;
//            TODO will not work
            grads.set(i, grad);
            float historyWeightDelta = hyperParams.getSpeed() * grad + hyperParams.getMoment() * safeGetPreviousDeltaWeight(i);
            historyWeightDeltas.set(i, historyWeightDelta);
//            weights.set(i,);
        }
    }

    protected Float safeGetPreviousDeltaWeight(int index) {
        try {
            return historyWeightDeltas.get(index);
        } catch (Throwable throwable) {
            return 0f;
        }
    }
}
