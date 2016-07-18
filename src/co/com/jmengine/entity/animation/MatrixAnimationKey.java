package co.com.jmengine.entity.animation;

import co.com.jmengine.math.Matrix;

public class MatrixAnimationKey {

	private Matrix matrix;
	private long time;
	
	public MatrixAnimationKey(long pTime, Matrix pMatrix){
		
		 time = pTime;
		 matrix = new Matrix(pMatrix.getM(), pMatrix.getN());
		 matrix.setMatrix(pMatrix);
		 
	}

	public Matrix getKey() {
		return matrix;
	}

	public long getTime() {
		return time;
	}
	
	
}
