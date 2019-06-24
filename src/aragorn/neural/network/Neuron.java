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

	public final void randomizeWeight() {
		randomizeWeight(Float.MIN_VALUE, Float.MAX_VALUE);
	}

	public abstract void randomizeWeight(double min, double max);

	@Override
	public abstract String toString();
}