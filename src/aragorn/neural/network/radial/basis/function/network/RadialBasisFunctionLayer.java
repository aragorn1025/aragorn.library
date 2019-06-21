package aragorn.neural.network.radial.basis.function.network;

import aragorn.neural.network.NeuralLayer;

class RadialBasisFunctionLayer extends NeuralLayer {

	RadialBasisFunctionLayer(int input_dimension, int output_dimension) {
		super(input_dimension, output_dimension);
		for (int j = 0; j < getNeuronNumber(); j++) {
			setNeuron(j, new RadialBasisFunctionNeuron(input_dimension));
		}
	}

	@Override
	public RadialBasisFunctionNeuron getNeuron(int j) {
		return (RadialBasisFunctionNeuron) super.getNeuron(j);
	}
}