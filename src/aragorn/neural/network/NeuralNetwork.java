package aragorn.neural.network;

import aragorn.util.MathVector;

public abstract class NeuralNetwork {

	private int input_dimension;

	private NeuralLayer[] neural_layers;

	protected NeuralNetwork(int input_dimension, int layer_number) {
		this.input_dimension = input_dimension;
		neural_layers = new NeuralLayer[layer_number];
	}

	public int getInputDimension() {
		return input_dimension;
	}

	public NeuralLayer getNeuralLayer(int i) {
		return neural_layers[i];
	}

	public int getNeuralLayerNumber() {
		return neural_layers.length;
	}

	public MathVector getOutput(MathVector input_vector) {
		MathVector vector = (MathVector) input_vector.clone();
		for (NeuralLayer layer : neural_layers) {
			vector = layer.getOutput(vector);
		}
		return vector;
	}

	public int getOutputDimension() {
		return getNeuralLayer(getNeuralLayerNumber() - 1).getOutputDimension();
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