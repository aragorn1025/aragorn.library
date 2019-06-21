package aragorn.neural.network;

import aragorn.util.MathVector;

public abstract class Neuron {

	private int input_dimension;

	protected Neuron(int input_dimension) {
		this.input_dimension = input_dimension;
	}

	public int getInputDimension() {
		return input_dimension;
	}

	public abstract double getOutput(MathVector input_vector);

	public abstract void randomizeWeight();

	@Override
	public abstract String toString();
}