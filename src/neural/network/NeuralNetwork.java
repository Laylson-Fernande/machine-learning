package neural.network;

import java.util.List;

public class NeuralNetwork {
	
	//The weights matrix for the input and hidden layer
	Matrix weights_ih;
	// The weights matrix for the hidden and output layer
	Matrix weights_ho;
	// The bias matrix for the hidden layer
	Matrix bias_h;
	// The bias matrix for the output layer
	Matrix bias_o;
	// The learning rate, a hyper-parameter used to control
	// the learning steps during optimization of weights
	double learningRate = 0.01;

	public NeuralNetwork(int input, int hidden, int output) {
		this.weights_ih = new Matrix(hidden, input);
		this.weights_ho = new Matrix(output, hidden);

		this.bias_h = new Matrix(hidden, 1);
		this.bias_o = new Matrix(output, 1);
	}

	public NeuralNetwork(int input, int hidden, int output, double rate) {
		this.weights_ih = new Matrix(hidden, input);
		this.weights_ho = new Matrix(output, hidden);

		this.bias_h = new Matrix(hidden, 1);
		this.bias_o = new Matrix(output, 1);

		learningRate = rate;
	}

	public List<Double> predict(double[] array) {
		Matrix input = Matrix.fromArray(array);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();

		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();

		return output.toArray();
	}

	public void train(double[] x, double[] y) {
		Matrix input = Matrix.fromArray(x);
		Matrix hidden = Matrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();

		Matrix output = Matrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();

		Matrix target = Matrix.fromArray(y);

		Matrix error = Matrix.subtract(target, output);
		Matrix gradient = output.dsigmoid();
		gradient.multiply(error);
		gradient.multiply(learningRate);

		Matrix hidden_t = Matrix.transpose(hidden);
		Matrix who_delta = Matrix.multiply(gradient, hidden_t);

		weights_ho.add(who_delta);
		bias_o.add(gradient);

		Matrix who_t = Matrix.transpose(weights_ho);
		Matrix hidden_errors = Matrix.multiply(who_t, error);

		Matrix h_gradient = hidden.dsigmoid();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(learningRate);

		Matrix i_t = Matrix.transpose(input);
		Matrix wih_delta = Matrix.multiply(h_gradient, i_t);

		weights_ih.add(wih_delta);
		bias_h.add(h_gradient);
	}

	public void fit(double[][] x, double[][] y, int epochs) {
		for (int i = 0; i < epochs; i++) {
			int sampleN = (int) (Math.random() * x.length);
			this.train(x[sampleN], y[sampleN]);
		}
	}

}
