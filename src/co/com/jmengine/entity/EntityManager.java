package co.com.jmengine.entity;

import java.util.ArrayList;

import co.com.jmengine.entity.loaders.xfile.XFileLoader;
import co.com.jmengine.interfaces.IEntityLoader;

public class EntityManager {

	public ArrayList<Entity3D> entities = new ArrayList<Entity3D>();
	public IEntityLoader entityLoader = new XFileLoader();
	
	
	
}
