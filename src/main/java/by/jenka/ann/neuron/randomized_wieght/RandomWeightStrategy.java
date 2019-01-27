package by.jenka.ann.neuron.randomized_wieght;

public interface RandomWeightStrategy {
    default float execute(Object[] params) {
        return 1;
    }
}
