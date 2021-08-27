package neural.network;

public class Layer {
	
	private Matrix weights;
	
	private Matrix bias;
	
	private Matrix hidden;
	
	public Layer(int input, int neurons) {
		this.weights = new Matrix(neurons, input);
		this.bias= new Matrix(neurons, 1);
	}
	
	public Matrix getActivation(Matrix input) {
		this.hidden = input;
		Matrix output = Matrix.multiply(weights, input);
		output.add(bias);
		output.sigmoid();
		return output;
	}
	
	public Matrix backPropagation(Matrix gradient) {
		Matrix transpose = Matrix.transpose(hidden);
		Matrix delta = Matrix.multiply(gradient, transpose);
		weights.add(delta);
		bias.add(gradient);
		return weights;
	}

	public Matrix getWeights() {
		return weights;
	}

	public void setWeights(Matrix weights) {
		this.weights = weights;
	}

	public Matrix getBias() {
		return bias;
	}

	public void setBias(Matrix bias) {
		this.bias = bias;
	}
	
	public Matrix getHidden() {
		return hidden;
	}

}
