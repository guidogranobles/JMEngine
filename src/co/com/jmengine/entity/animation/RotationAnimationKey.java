package co.com.jmengine.entity.animation;

import co.com.jmengine.math.Quaternion;

public class RotationAnimationKey {

	private Quaternion quaternion;
	private long time;
	
	public RotationAnimationKey(long pTime, double x, double y, double z, double w){
		
		time = pTime;
		quaternion = new Quaternion(x,y,z,w);
		
	}

	public Quaternion getKey() {
		return quaternion;
	}

	public long getTime() {
		return time;
	}
	
	
}
