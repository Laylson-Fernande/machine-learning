package neural.network;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	double[][] data;
	int rows;
	int cols;

	public Matrix(int rows, int cols) {
		data = new double[rows][cols];
		this.rows = rows;
		this.cols = cols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = Math.random() * 2 - 1;
			}
		}
	}

	public void add(double scaler) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] += scaler;
			}
		}
	}

	public void add(Matrix matrix) {
		if (this.rows != matrix.rows || this.cols != matrix.cols) {
			System.out.println("Shape Mismatch");
		} else {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					this.data[i][j] += matrix.data[i][j];
				}
			}
		}
	}

	public static Matrix subtract(Matrix a, Matrix b) {
		Matrix result = new Matrix(a.rows, a.cols);
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				result.data[i][j] = a.data[i][j] - b.data[i][j];
			}
		}
		return result;
	}

	public static Matrix transpose(Matrix matrix) {
		Matrix result = new Matrix(matrix.cols, matrix.rows);
		for (int i = 0; i < matrix.rows; i++) {
			for (int j = 0; j < matrix.cols; j++) {
				result.data[j][i] = matrix.data[i][j];
			}
		}
		return result;
	}

	public static Matrix multiply(Matrix a, Matrix b) {
		Matrix result = new Matrix(a.rows, b.cols);
		for (int i = 0; i < result.rows; i++) {
			for (int j = 0; j < result.cols; j++) {
				double sum = 0;
				for (int k = 0; k < a.cols; k++) {
					sum += a.data[i][k] * b.data[k][j];
				}
				result.data[i][j] = sum;
			}
		}
		return result;
	}

	public void multiply(Matrix matrix) {
		for (int i = 0; i < matrix.rows; i++) {
			for (int j = 0; j < matrix.cols; j++) {
				this.data[i][j] *= matrix.data[i][j];
			}
		}
	}

	public void multiply(double value) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] *= value;
			}
		}
	}

	public void sigmoid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));
			}
		}
	}

	public Matrix dsigmoid() {
		Matrix result = new Matrix(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);
			}
		}
		return result;
	}

	public static Matrix fromArray(double[] array) {
		Matrix result = new Matrix(array.length, 1);
		for (int i = 0; i < array.length; i++) {
			result.data[i][0] = array[i];
		}
		return result;
	}

	public List<Double> toArray() {
		List<Double> result = new ArrayList<Double>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result.add(data[i][j]);
			}
		}
		return result;
	}
	
	public void print()
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				System.out.print(this.data[i][j]+"");
			}
			System.out.println();
		}
	}

}
