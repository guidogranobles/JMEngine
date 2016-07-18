package co.com.jmengine.interfaces;

import co.com.jmengine.entity.Entity3D;

public interface IEntityLoader {

	public Entity3D getEntity();
	void loadEntity(String pathName) throws Exception;
	
}
