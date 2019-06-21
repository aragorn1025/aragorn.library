package aragorn.neural.network.radial.basis.function.network;

import aragorn.neural.network.NeuralNetwork;

public class RadialBasisFunctionNetwork extends NeuralNetwork {

	public RadialBasisFunctionNetwork(int input_dimension, int output_dimension, int radial_basis_function_neuron_number) {
		super(input_dimension, 2);
		setNeuralLayer(0, new RadialBasisFunctionLayer(input_dimension, radial_basis_function_neuron_number));
		setNeuralLayer(1, new OutputLayer(radial_basis_function_neuron_number, output_dimension));
	}

	public OutputLayer getOutputLayer() {
		return (OutputLayer) super.getNeuralLayer(1);
	}

	public RadialBasisFunctionLayer getRadialBasisFunctionLayer() {
		return (RadialBasisFunctionLayer) super.getNeuralLayer(0);
	}

	public void load(RadialBasisFunctionNetworkWeight weight) {
		for (int j = 0; j < getRadialBasisFunctionLayer().getNeuronNumber(); j++) {
			getRadialBasisFunctionLayer().getNeuron(j).setSigma(weight.getSigma(j));
		}
		for (int j = 0; j < getRadialBasisFunctionLayer().getNeuronNumber(); j++) {
			for (int i = 0; i < getInputDimension(); i++) {
				getRadialBasisFunctionLayer().getNeuron(j).setM(i, weight.getM(j, i));
			}
		}
		for (int j = 0; j < getOutputDimension(); j++) {
			for (int i = 0; i < getRadialBasisFunctionLayer().getNeuronNumber() + 1; i++) {
				getOutputLayer().getNeuron(j).setW(i, weight.getW(j, i));
			}
		}
	}
}