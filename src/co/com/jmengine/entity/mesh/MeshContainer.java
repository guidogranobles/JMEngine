package co.com.jmengine.entity.mesh;

import java.util.ArrayList;

public class MeshContainer {

	int numMeshes;
	Mesh finalMesh;
	public ArrayList<MeshCoord> meshCoords = new ArrayList<MeshCoord>();
	public ArrayList<Mesh> meshes = new ArrayList<Mesh>();
	
	public MeshContainer(){
		finalMesh = new Mesh();
	}
	
}
