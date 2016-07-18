package co.com.jmengine.math;

public class Quaternion {

	private final double x, y, z, w;

	public Quaternion(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	public String toString() {
		return x + " + " + y + "i + " + z + "j + " + w + "k";
	}

	public double norm() {
		return Math.sqrt(x * x + y * y + z * z + w * w);
	}

	public Quaternion conjugate() {
		return new Quaternion(x, -y, -z, -w);
	}

	public Quaternion plus(Quaternion b) {
		Quaternion a = this;
		return new Quaternion(a.x + b.x, a.y + b.y, a.z + b.z, a.w + b.w);
	}

	public double dot(Quaternion qT) {
		return (x * qT.x + y * qT.y + z * qT.z + w * qT.w);
	}

	public Quaternion product(Quaternion b) {
		Quaternion a = this;
		double y0 = a.x * b.x - a.y * b.y - a.z * b.z - a.w * b.w;
		double y1 = a.x * b.y + a.y * b.x + a.z * b.w - a.w * b.z;
		double y2 = a.x * b.z - a.y * b.w + a.z * b.x + a.w * b.y;
		double y3 = a.x * b.w + a.y * b.z - a.z * b.y + a.w * b.x;
		return new Quaternion(y0, y1, y2, y3);
	}

	public Quaternion multiply(double value) {
		Quaternion a = this;
		double y0 = a.x * value;
		double y1 = a.y * value;
		double y2 = a.z * value;
		double y3 = a.w * value;
		return new Quaternion(y0, y1, y2, y3);
	}

	public Quaternion inverse() {
		double d = x * x + y * y + z * z + w * w;
		return new Quaternion(x / d, -y / d, -z / d, -w / d);
	}

	public Quaternion divides(Quaternion b) {
		Quaternion a = this;
		return a.inverse().product(b);
	}

	public Quaternion slerp(double angleRot, Quaternion qt) {

		double angleCos = qt.dot(this);
		double angle = QAcos(angleCos);

		if (angle < Float.MIN_VALUE) {
			return this;
		}

		double invAngleSin = (1.0f / Math.sin(angle));

		double Coeff0 = Math.sin((1 - angleRot) * angle) * invAngleSin;
		double Coeff1 = Math.sin(angleRot * angle) * invAngleSin;
		
		return this.multiply(Coeff0).plus(qt.multiply(Coeff1));
	}

	private double QAcos(double value) {
		if (-1.0f < value) {
			if (value < 1.0f)
				return Math.acos(value);
			else
				return 0.0f;
		} else
			return Math.PI;
	}
	
	
}
