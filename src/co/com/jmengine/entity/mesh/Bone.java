package co.com.jmengine.entity.mesh;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import co.com.jmengine.math.Matrix;

public class Bone {

	public Matrix matrixCombined;
	public Matrix matrixTransform;
	public String name;
	public int numVertices;
	public SkinWeights skinWeights;
	public int indexParent;
	public int index;
	//public LinkedList<Bone> boneSiblings = new LinkedList<Bone>();
	//public LinkedList<Bone> boneChilds = new LinkedList<Bone>();
	
	public Bone(String pName){
		name = pName;
	}
		
	public Bone(String pName, int numVertices){
		name = pName;
		matrixCombined = new Matrix(4,4);
		matrixTransform = new Matrix(4,4);
		skinWeights = new SkinWeights();
		skinWeights.skinOffSet = new Matrix(4,4);
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(numVertices);
	    vbb.order(ByteOrder.nativeOrder());
	    skinWeights.vertexIndex = vbb.asIntBuffer();	    
	    skinWeights.weights = vbb.asFloatBuffer();	    	      
		 
	}
}
