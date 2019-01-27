package by.jenka.ann.neuron.activation_function;

public interface ActivationFunctionStrategy {
    default float execute(float input) {
        return input;
    }

    default float calculateDerivative(float output) {
        return 0;
    }
}
