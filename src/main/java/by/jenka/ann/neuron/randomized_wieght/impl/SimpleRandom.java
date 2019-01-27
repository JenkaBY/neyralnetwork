package by.jenka.ann.neuron.randomized_wieght.impl;

import by.jenka.ann.neuron.randomized_wieght.RandomWeightStrategy;

public class SimpleRandom implements RandomWeightStrategy {
    @Override
    public float execute(Object[] params) {
        int neuronCount = (Integer) params[0];
        return (float) (Math.random() < 0.5 ?
                Math.random() * 0.3 + (2 / neuronCount)
                :
                -Math.random() * 0.3 - (2 / neuronCount));
    }
}
