package aragorn.neural.network.radial.basis.function.network;

import aragorn.neural.network.NeuralLayer;

class OutputLayer extends NeuralLayer {

	OutputLayer(int input_dimension, int output_dimension) {
		super(output_dimension);
		for (int j = 0; j < getNeuronNumber(); j++) {
			setNeuron(j, new OutputNeuron(input_dimension));
		}
	}

	void setW(int j, int i, double w_j_i) {
		getNeuron(j).setW(i, w_j_i);
	}

	@Override
	protected OutputNeuron getNeuron(int i) {
		return (OutputNeuron) super.getNeuron(i);
	}
}