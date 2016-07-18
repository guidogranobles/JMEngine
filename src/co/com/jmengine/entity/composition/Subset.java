package co.com.jmengine.entity.composition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class Subset {

	public int numFaces;
	public IntBuffer subSetFaces;
	
	public Subset(int pNumFaces){
		numFaces = pNumFaces; 
		ByteBuffer vbb = ByteBuffer.allocateDirect(numFaces);
	    vbb.order(ByteOrder.nativeOrder());
	    subSetFaces = vbb.asIntBuffer();	  
	}
}
