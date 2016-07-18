package co.com.jmengine.entity.composition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class FloatDataBuffer {

	public FloatBuffer buffer;    
	public int elementSize; 
	public int size;
    
    public FloatDataBuffer(int numVertices, int objectSize) {
    	elementSize = objectSize;
    	size = numVertices;
    	ByteBuffer vbb = ByteBuffer.allocateDirect((objectSize * numVertices)*Float.SIZE);
        vbb.order(ByteOrder.nativeOrder());
        buffer = vbb.asFloatBuffer();      
    }
}
