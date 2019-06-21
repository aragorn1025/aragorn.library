package aragorn.neural.network.radial.basis.function.network;

import aragorn.neural.network.NeuralLayer;

class OutputLayer extends NeuralLayer {

	OutputLayer(int input_dimension, int output_dimension) {
		super(input_dimension, output_dimension);
		for (int j = 0; j < getNeuronNumber(); j++) {
			setNeuron(j, new OutputNeuron(input_dimension));
		}
	}

	@Override
	public OutputNeuron getNeuron(int j) {
		return (OutputNeuron) super.getNeuron(j);
	}
}