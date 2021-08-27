package neural.network;

import java.util.List;

public class NeuralNetworkMain {
	public static void main(String[] args) {
		double[][] x = {{0,1,},{1,0},{0,0}};
		double[][] y = {{1,0},{0,1},{1,0}};

		NeuralNetwork network = new NeuralNetwork(2, 10, 2,0.01);
		network.fit(x, y, 100000);
		
		List<Double> output;
		double[][] input = {{0,1,},{1,0},{0,0}};
		for (double d[]:input) {
			output = network.predict(d);
			System.out.println(output.toString());
		}
	}
}
