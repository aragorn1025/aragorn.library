package aragorn.neural.network.radial.basis.function.network;

import aragorn.neural.network.Neuron;
import aragorn.util.MathVector;

class OutputNeuron extends Neuron {

	private MathVector w;

	OutputNeuron(int input_dimension) {
		super(input_dimension);
		this.w = new MathVector(input_dimension + 1);
	}

	@Override
	public double getOutput(MathVector input_vector) {
		if (1 + input_vector.getDimension() != w.getDimension())
			throw new IllegalArgumentException("The dimension of the input vector mismatched.");
		MathVector concatenated_vector = MathVector.concatenate(new MathVector(1), input_vector);
		concatenated_vector.setComponent(0, 1);
		return MathVector.getInnerProduct(concatenated_vector, w);
	}

	public double getW(int i) {
		return w.getComponent(i);
	}

	@Override
	public void randomizeWeight() {
		for (int i = 0; i < w.getDimension(); i++) {
			w.setComponent(i, Math.random());
		}
	}

	public void setW(int i, double w_i) {
		w.setComponent(i, w_i);
	}

	@Override
	public String toString() {
		return String.format("\t\t%s [w = %s]", getClass().getSimpleName(), w.toString());
	}
}