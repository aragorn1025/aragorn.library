package aragorn.neural.network.radial.basis.function.network;

import aragorn.neural.network.Neuron;
import aragorn.util.MathVector;

class RadialBasisFunctionNeuron extends Neuron {

	private MathVector m;

	private double sigma;

	RadialBasisFunctionNeuron(int input_dimension) {
		super(input_dimension);
		this.m = new MathVector(input_dimension);
		this.sigma = 0.0;
	}

	public double getM(int i) {
		return m.getComponent(i);
	}

	@Override
	public double getOutput(MathVector x) {
		MathVector vector = MathVector.add(x, m.getNegative());
		return Math.exp(-0.5 * MathVector.getInnerProduct(vector, vector) / (sigma * sigma));
	}

	public double getSigma() {
		return sigma;
	}

	@Override
	public void randomizeWeight() {
		for (int i = 0; i < m.getDimension(); i++) {
			m.setComponent(i, Math.random());
		}
		sigma = Math.random();
	}

	public void setM(int i, double m_i) {
		m.setComponent(i, m_i);
	}

	public void setSigma(double sigma) {
		this.sigma = sigma;
	}

	@Override
	public String toString() {
		return String.format("\t\t%s [m = %s, sigma = %.8f]", getClass().getSimpleName(), m.toString(), sigma);
	}
}