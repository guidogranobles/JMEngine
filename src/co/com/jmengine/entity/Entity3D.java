package co.com.jmengine.entity;

import java.util.ArrayList;
import java.util.LinkedList;

import co.com.jmengine.entity.animation.AnimationSet;
import co.com.jmengine.entity.mesh.Bone;
import co.com.jmengine.entity.mesh.MeshContainer;

public class Entity3D {

	public String name;
	public MeshContainer meshContainer;
	public LinkedList<Bone> bones = new LinkedList<Bone>();
	public ArrayList<AnimationSet> animationSets = new ArrayList<AnimationSet>();
	
	public Entity3D(){
		meshContainer = new MeshContainer();		
	}
}
