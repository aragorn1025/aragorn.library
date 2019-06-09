package aragorn.neural.network;

import aragorn.util.MathVector;

public abstract class NeuralNetwork {

	private NeuralLayer[] neural_layers;

	protected NeuralNetwork(int layer_number) {
		neural_layers = new NeuralLayer[layer_number];
	}

	protected NeuralLayer getNeuralLayer(int i) {
		return neural_layers[i];
	}

	protected int getNeuralLayerNumber() {
		return neural_layers.length;
	}

	public MathVector getOutput(MathVector input_vector) {
		MathVector vector = (MathVector) input_vector.clone();
		for (NeuralLayer layer : neural_layers) {
			vector = layer.getOutput(vector);
		}
		return vector;
	}

	public void randomizeWeight() {
		for (NeuralLayer layer : neural_layers) {
			layer.randomizeWeight();
		}
	}

	protected void setNeuralLayer(int i, NeuralLayer neural_layer) {
		neural_layers[i] = neural_layer;
	}

	@Override
	public String toString() {
		String val = "";
		for (NeuralLayer layer : neural_layers) {
			val += String.format("%s%n", layer.toString());
		}
		return String.format("%s [%n%s]", getClass().getSimpleName(), val);
	}
}