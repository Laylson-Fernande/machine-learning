package neural.network;

import java.util.LinkedList;
import java.util.List;

public class NeuralNetwork {
	
	// list with weights and bias by layers
	List<Layer> layersList = new LinkedList<Layer>();
	// The learning rate, a hyper-parameter used to control
	// the learning steps during optimization of weights
	double learningRate = 0.01;

	public NeuralNetwork(int input, int output) {
		this.init(input, 1, input, output, 0.01);
	}
	
	public NeuralNetwork(int input, int neurons, int output, double rate) {
		this.init(input, 1, neurons, output, rate);
	}

	public NeuralNetwork(int input, int layers, int output) {
		this.init(input, layers, input, output, 0.01);
	}

	public NeuralNetwork(int input, int layers, int neurons, int output) {
		this.init(input, layers, neurons, output, 0.01);
	}

	public NeuralNetwork(int input, int layers, int neurons, int output, double rate) {
		this.init(input, layers, neurons, output, rate);
	}

	private void init(int input, int layers, int neurons, int output, double rate) {
		for (int i = 0; i < layers; i++) {
			if (i == 0) {
				Layer layer = new Layer(input, neurons);
				layersList.add(layer);
			} else {
				Layer layer = new Layer(neurons, neurons);
				layersList.add(layer);
			}
		}
		Layer layer = new Layer(neurons, output);
		layersList.add(layer);

		learningRate = rate;
	}

	public List<Double> predict(double[] array) {
		Matrix output = Matrix.fromArray(array);
		for(Layer layer : layersList) {
			output = layer.getActivation(output);
		}
		return output.toArray();
	}

	public void train(double[] x, double[] y) {
		
		Matrix output = Matrix.fromArray(x);
		for(Layer layer : layersList) {
			output = layer.getActivation(output);
		}
		
		Matrix target = Matrix.fromArray(y);

		Matrix error = Matrix.subtract(target, output);
		Matrix gradient = output.dsigmoid();
		for(int i = layersList.size()-1;i>=0;i--) {
			Layer layer = layersList.get(i);
			
			gradient.multiply(error);
			gradient.multiply(learningRate);
			gradient = layer.backPropagation(gradient);
			target = Matrix.transpose(layer.getWeights());
			error = Matrix.multiply(target, error);
			gradient = layer.getHidden().dsigmoid();
		}
	}

	public void fit(double[][] x, double[][] y, int epochs) {
		for (int i = 0; i < epochs; i++) {
			if ((i % 5000) == 0) {
				System.out.println(i);
			}
			int sampleN = (int) (Math.random() * x.length);
			this.train(x[sampleN], y[sampleN]);
		}
	}

}
