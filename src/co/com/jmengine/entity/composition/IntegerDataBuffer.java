package co.com.jmengine.entity.composition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class IntegerDataBuffer {

	public IntBuffer buffer;    
	public int elementSize; 
	public int size;
    
    public IntegerDataBuffer(int numElements, int objectSize) {
    	elementSize = objectSize;
    	size = numElements;
    	ByteBuffer vbb = ByteBuffer.allocateDirect((objectSize * numElements)*Integer.SIZE);
        vbb.order(ByteOrder.nativeOrder());
        buffer = vbb.asIntBuffer();
       
    }
}
