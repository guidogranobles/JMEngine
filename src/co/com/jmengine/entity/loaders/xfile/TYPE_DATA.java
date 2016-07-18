package co.com.jmengine.entity.loaders.xfile;

public enum TYPE_DATA {

     Frame,
     FrameTransformMatrix,
     Mesh,
     MeshNormals,
     MeshMaterialList,
     Material,
     VertexDuplicationIndices;
        
     
     public TYPE_DATA getType(String str){
    	 TYPE_DATA type = null;
    	 if(str.startsWith("Frame")){
    		 type = Frame;
    	 }
    	 
    	 return type;
     }
    
}
