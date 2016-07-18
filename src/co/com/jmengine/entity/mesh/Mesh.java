package co.com.jmengine.entity.mesh;

import java.util.ArrayList;

import co.com.jmengine.entity.composition.FloatDataBuffer;
import co.com.jmengine.entity.composition.IntegerDataBuffer;
import co.com.jmengine.entity.composition.MaterialList;
import co.com.jmengine.entity.composition.Subset;

public class Mesh {

	public int numFaces;
	public int numVertices;
	public int numTextCoords;
	public int numNormals;
	public int index;
	public int indexParent;
	
	public IntegerDataBuffer faces;
	public IntegerDataBuffer normalsFaces;
	public FloatDataBuffer vertexBuffer;
	public FloatDataBuffer normalsBuffer;
	public FloatDataBuffer textCoordBuffer;
	public MaterialList materials;
	public ArrayList<Subset> subsets = new  ArrayList<Subset>();
	
	public Mesh(){
		
	}
	
	
}
