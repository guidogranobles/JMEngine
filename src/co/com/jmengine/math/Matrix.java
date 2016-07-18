package co.com.jmengine.math;

public class Matrix {

	private double[][] matrix;
	private int m, n;

	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		matrix = new double[m][n];
	}

	public void zero() {
		for (int i = 0; i < matrix[m].length; i++) {
			for (int j = 0; j < matrix[n].length; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	public void setValue(int m, int n, double value) {
		matrix[m][n] = value;
	}

	public double getValue(int m, int n) {
		return matrix[m][n];
	}

	public double[][] getValues() {
		return matrix;
	}

	public Matrix transpose() {
		Matrix matTrans = new Matrix(n, m);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matTrans.setValue(j, i, matrix[i][j]);
			}
		}
		return matTrans;
	}

	public Matrix plus(Matrix source) {
		if (source.m != m || source.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}
		Matrix result = new Matrix(m, n);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result.setValue(i, j, matrix[i][j] + source.getValue(i, j));
			}
		}
		return result;
	}

	public Matrix add(Matrix source) {
		if (source.m != m || source.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				setValue(i, j, matrix[i][j] + source.getValue(i, j));
			}
		}
		return this;
	}

	public Matrix minus(Matrix source) {
		if (source.m != m || source.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}
		Matrix result = new Matrix(m, n);

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result.setValue(i, j, matrix[i][j] - source.getValue(i, j));
			}
		}
		return result;
	}

	public Matrix sub(Matrix source) {
		if (source.m != m || source.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				setValue(i, j, matrix[i][j] - source.getValue(i, j));
			}
		}
		return this;
	}

	public Matrix multiply(Matrix source) {
		if (source.m != m || source.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}
		Matrix result = new Matrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result.setValue(i, j, matrix[i][j] * source.getValue(i, j));
			}
		}
		return result;
	}

	public Matrix product(Matrix source) {
		if (source.m != m || source.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				setValue(i, j, matrix[i][j] * source.getValue(i, j));
			}
		}
		return this;
	}

	public Matrix scale(double coef) {
		Matrix result = new Matrix(m, n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result.setValue(i, j, matrix[i][j] * coef);
			}
		}
		return result;
	}

	public Matrix selfScale(double coef) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				setValue(i, j, matrix[i][j] * coef);
			}
		}
		return this;
	}

	public Matrix dosProduct(Matrix source) {
		if (source.m != n) {
			throw new IllegalArgumentException(
					"Matrix inner dimensions must be equals.");
		}
		Matrix result = new Matrix(m, source.n);

		double[] Bcolj = new double[n];
		for (int j = 0; j < source.n; j++) {
			for (int k = 0; k < n; k++) {
				Bcolj[k] = source.getValue(k, j);
			}
			for (int i = 0; i < m; i++) {
				double[] Arowi = matrix[i];
				double s = 0;
				for (int k = 0; k < n; k++) {
					s += Arowi[k] * Bcolj[k];
				}
				result.setValue(i, j, s);
			}
		}
		return result;
	}

	public void makeidentity() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = (i == j ? 1.0 : 0.0);
			}
		}
	}
	
	public void setMatrix(Matrix mat){
		if (mat.m != m || mat.n != n) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be equals.");
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = mat.getValue(i,j);
			}
		}
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}
	
	

}
