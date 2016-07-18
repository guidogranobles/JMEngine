package co.com.jmengine.entity.composition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class DataBuffer {

	public FloatBuffer buffer;
   // public int offset; 
   // public int vboLen;
    
	public int elementSize; 
	public int size;
    
    public DataBuffer(int numVertices, int objectSize) {
    	elementSize = objectSize;
    	size = numVertices;
    	ByteBuffer vbb = ByteBuffer.allocateDirect(objectSize * numVertices);
        vbb.order(ByteOrder.nativeOrder());
        buffer = vbb.asFloatBuffer();
       
    }
}
