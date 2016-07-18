package co.com.jmengine.math;

public class Vector {
		
	private double x;
	private double y;
	private double z;
	
	public static final Vector zero  = new Vector(0,0,0);
	public static final Vector xAxis = new Vector(1,0,0);
	public static final Vector yAxis = new Vector(0,1,0);
	public static final Vector zAxis = new Vector(0,0,1);
	
		
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}
	
		
	public Vector(double px,double py,double pz) {
		x = px;
		y = py;
		z = pz;
	}
	
	
	public Vector(Vector copy) {
		x = copy.x;
		y = copy.y;
		z = copy.z;
	}
	
	
	public Vector copy() {
		return new Vector(this);
	}
	
	
	public double dot(Vector vec) {
		return x*vec.x + y*vec.y + z*vec.z;
	}
	
	
	static public double dot(Vector v1,Vector v2) {
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}
	

	public Vector cross(Vector vec) {
		return new Vector(y*vec.z - z*vec.y,
						  z*vec.x - x*vec.z,
						  x*vec.y - y*vec.x);
	}
	
	public static Vector cross(Vector v1, Vector v2) {
		return new Vector(v1.y*v2.z - v1.z*v2.y,
				v1.z*v2.x - v1.x*v2.z,
				v1.x*v2.y - v1.y*v2.x);
	}


	
	public double lengthSquared() {
		return x*x+y*y+z*z;
	}
	
	
	public double length() {
		return Math.sqrt(x*x+y*y+z*z);
	}

	public double normalize() {
		double length,square = x*x+y*y+z*z;
		
		if(square > 0) {
			length = Math.sqrt(square);
			x /= length;
			y /= length;
			z /= length;
		} else {
			length = 0;
		}
		
		return length;
	}
	
	static public Vector normalize(Vector v) {
		double length = Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z);
		
		if(length>0) {
			return new Vector(v.x/length,v.y/length,v.z/length);
		} else {
			return new Vector(v);
		}
	}
	

	public void add(Vector v) {
		x+=v.x;
		y+=v.y;
		z+=v.z;
	}
	
	
	static public Vector add(Vector v1,Vector v2) {
		return new Vector(v1.x+v2.x,v1.y+v2.y,v1.z+v2.z);
	}
	
	
	
	public void set(double px,double py,double pz) {
		x = px;
		y = py;
		z = pz;
	}
	
	
	public void addScaled(Vector v,double s) {
		x+=v.x*s;
		y+=v.y*s;
		z+=v.z*s;
	}
	

	static public Vector addScaled(Vector v1,Vector v2,double s) {
		return new Vector(v1.x+v2.x*s,v1.y+v2.y*s,v1.z+v2.z*s);
	}
	

	public void sub(Vector v) {
		x-=v.x;
		y-=v.y;
		z-=v.z;
	}
	
	
	
	static public Vector sub(Vector v1,Vector v2) {
		return new Vector(v1.x-v2.x,v1.y-v2.y,v1.z-v2.z);
	}
	
	

	public void set(Vector v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}
	

	public void mul(double f) {
		x*=f;
		y*=f;
		z*=f;
	}
	
	
	static public Vector mul(Vector v,double s) {
		return new Vector(v.x*s,v.y*s,v.z*s);
	}
	

	
	public void componentDiv(Vector v) {
		x/=v.x;
		y/=v.y;
		z/=v.z;
	}
	

	static public Vector componentDiv(Vector v1,Vector v2) {
		return new Vector(
				v1.x / v2.x,
				v1.y / v2.y,
				v1.z / v2.z);
	}
	
	public String toString() {
		return "V("+x+","+y+","+z+")";
	}
	
}