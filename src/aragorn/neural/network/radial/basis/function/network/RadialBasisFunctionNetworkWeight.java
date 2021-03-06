package aragorn.neural.network.radial.basis.function.network;

import java.util.Arrays;
import aragorn.util.MathVector;

public class RadialBasisFunctionNetworkWeight implements Cloneable {

	private double[] sigma;

	private MathVector[] m;

	private MathVector[] w;

	public RadialBasisFunctionNetworkWeight(int input_dimension, int output_dimension, int radial_basis_function_neuron_number) {
		this.sigma = new double[radial_basis_function_neuron_number];
		this.m = new MathVector[radial_basis_function_neuron_number];
		for (int i = 0; i < m.length; i++) {
			m[i] = new MathVector(input_dimension);
		}
		this.w = new MathVector[output_dimension];
		for (int i = 0; i < w.length; i++) {
			w[i] = new MathVector(radial_basis_function_neuron_number + 1);
		}
	}

	public RadialBasisFunctionNetworkWeight(RadialBasisFunctionNetwork network) {
		this(network.getInputDimension(), network.getOutputDimension(), network.getRadialBasisFunctionLayer().getNeuronNumber());
		for (int j = 0; j < sigma.length; j++) {
			sigma[j] = network.getRadialBasisFunctionLayer().getNeuron(j).getSigma();
		}
		for (int j = 0; j < m.length; j++) {
			for (int i = 0; i < m[j].getDimension(); i++) {
				m[j].setComponent(i, network.getRadialBasisFunctionLayer().getNeuron(j).getM(i));
			}
		}
		for (int j = 0; j < w.length; j++) {
			for (int i = 0; i < w[j].getDimension(); i++) {
				w[j].setComponent(i, network.getOutputLayer().getNeuron(j).getW(i));
			}
		}
	}

	@Override
	public Object clone() {
		try {
			RadialBasisFunctionNetworkWeight val = (RadialBasisFunctionNetworkWeight) super.clone();
			val.sigma = this.sigma.clone();
			val.m = Arrays.copyOf(this.m, this.m.length);
			for (int i = 0; i < m.length; i++) {
				val.m[i] = (MathVector) m[i].clone();
			}
			val.w = Arrays.copyOf(this.w, this.w.length);
			for (int i = 0; i < w.length; i++) {
				val.w[i] = (MathVector) w[i].clone();
			}
			return val;
		} catch (CloneNotSupportedException e) {
			throw new UnknownError("Unknown error.");
		}
	}

	public double getM(int j, int i) {
		return m[j].getComponent(i);
	}

	public int getMDimension() {
		return m[0].getDimension();
	}

	public int getMSize() {
		return m.length;
	}

	public double getSigma(int j) {
		return sigma[j];
	}

	public int getSigmaSize() {
		return sigma.length;
	}

	public double getW(int j, int i) {
		return w[j].getComponent(i);
	}

	public int getWDimension() {
		return w[0].getDimension();
	}

	public int getWSize() {
		return w.length;
	}

	public void randomized() {
		randomized(Float.MIN_VALUE, Float.MAX_VALUE);
	}

	public void randomized(double min, double max) {
		for (int i = 0; i < sigma.length; i++) {
			sigma[i] = Math.random() * (max - min) + min;
		}
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].getDimension(); j++) {
				m[i].setComponent(j, Math.random() * (max - min) + min);
			}
		}
		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w[i].getDimension(); j++) {
				w[i].setComponent(j, Math.random() * (max - min) + min);
			}
		}
	}

	public void setM(int j, int i, double m_j_i) {
		m[j].setComponent(i, m_j_i);
	}

	public void setSigma(int j, double sigma_j) {
		sigma[j] = sigma_j;
	}

	public void setW(int j, int i, double w_j_i) {
		w[j].setComponent(i, w_j_i);
	}

	@Override
	public String toString() {
		String val = "RadialBasisFunctionNetwork [\r\n\tRadialBasisFunctionLayer [\r\n";
		for (int i = 0; i < m.length; i++) {
			val += String.format("\t\t%s [m = %s, sigma = %.8f]\r\n", "RadialBasisFunctionNeuron", m[i], sigma[i]);
		}
		val += "\t]\r\n\tOutputLayer [\r\n";
		for (int i = 0; i < w.length; i++) {
			val += String.format("\t\t%s [w = %s]\r\n", "OutputNeuron", w[i]);
		}
		val += "\t]\r\n]";
		return val;
	}
}