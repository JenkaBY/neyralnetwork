package by.jenka.ann.training;

public class TrainingSet {
    private float[] input;
    private float[] expectedResult;

    public TrainingSet(float[] input, float[] expectedResult) {
        this.input = input;
        this.expectedResult = expectedResult;
    }

    public float[] getInput() {
        return input;
    }

    public void setInput(float[] input) {
        this.input = input;
    }

    public float[] getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(float[] expectedResult) {
        this.expectedResult = expectedResult;
    }
}
