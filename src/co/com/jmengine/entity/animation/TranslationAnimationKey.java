package co.com.jmengine.entity.animation;

import co.com.jmengine.math.Vector;

public class TranslationAnimationKey {

	private Vector v;
	long time;
	
	public TranslationAnimationKey(long pTime, double x, double y, double z){
		
		  time = pTime;
		  v = new Vector(x,y,z);
	}

	public Vector getKey() {
		return v;
	}

	public long getTime() {
		return time;
	}
	
}
