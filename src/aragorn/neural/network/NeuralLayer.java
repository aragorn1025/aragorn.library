package aragorn.neural.network;

import aragorn.util.MathVector;

public abstract class NeuralLayer {

	private int input_dimension;

	private Neuron[] neurons;

	protected NeuralLayer(int input_dimension, int neuron_number) {
		this.input_dimension = input_dimension;
		neurons = new Neuron[neuron_number];
	}

	public int getInputDimension() {
		return input_dimension;
	}

	public Neuron getNeuron(int i) {
		return neurons[i];
	}

	public int getNeuronNumber() {
		return neurons.length;
	}

	public MathVector getOutput(MathVector input_vector) {
		MathVector val = new MathVector(getNeuronNumber());
		for (int i = 0; i < getNeuronNumber(); i++) {
			val.setComponent(i, neurons[i].getOutput(input_vector));
		}
		return val;
	}

	public int getOutputDimension() {
		return neurons.length;
	}

	public void randomizeWeight() {
		for (Neuron neuron : neurons) {
			neuron.randomizeWeight();
		}
	}

	protected void setNeuron(int i, Neuron neuron) {
		if (neuron.getInputDimension() != input_dimension)
			throw new IllegalArgumentException("The input dimension should be the same.");
		this.neurons[i] = neuron;
	}

	@Override
	public String toString() {
		String val = "";
		for (Neuron neuron : neurons) {
			val += String.format("%s%n", neuron.toString());
		}
		return String.format("\t%s [%n%s\t]", getClass().getSimpleName(), val);
	}
}