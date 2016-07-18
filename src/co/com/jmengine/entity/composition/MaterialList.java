package co.com.jmengine.entity.composition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class MaterialList {

	public ArrayList<Material> materials = new ArrayList<Material>();
	public IntBuffer materialFaces;
	public int numMaterialFaces;
	public int numMaterials;
	
	public MaterialList(int pNumMaterials, int pNumMaterialFaces){
		numMaterialFaces = pNumMaterialFaces;
		ByteBuffer vbb = ByteBuffer.allocateDirect(numMaterialFaces*Integer.SIZE);
	    vbb.order(ByteOrder.nativeOrder());
	    materialFaces = vbb.asIntBuffer();	  
	    numMaterials = pNumMaterials;

	}
}
