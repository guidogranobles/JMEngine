package co.com.jmengine.entity.loaders.xfile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

import co.com.jmengine.entity.Entity3D;
import co.com.jmengine.entity.composition.FloatDataBuffer;
import co.com.jmengine.entity.composition.IntegerDataBuffer;
import co.com.jmengine.entity.composition.Material;
import co.com.jmengine.entity.composition.MaterialList;
import co.com.jmengine.entity.mesh.Bone;
import co.com.jmengine.entity.mesh.Mesh;
import co.com.jmengine.interfaces.IEntityLoader;
import co.com.jmengine.math.Matrix;

public class XFileLoader implements IEntityLoader {

	private final String MAJOR_VERSION = "03";
	private final String MINOR_VERSION = "03";
	private final String TYPE_FILE = "txt";
	private TYPE_DATA DATA;
	private final int NAME_BONE = 1;

	private Entity3D entity;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
	Bone boneRoot;
	private int contFrames;
	private int contMeshes;
	private LinkedList<Bone> boneList = new LinkedList<Bone>();
	private LinkedList<Bone> boneListAux = new LinkedList<Bone>();
	private int indexParent;

	public XFileLoader() {
		entity = new Entity3D();
	}

	@Override
	public void loadEntity(String pathName) throws Exception {

		is = new FileInputStream(pathName);
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		String line = null;
		line = br.readLine();

		checkHeader(line);

		while (!((line = br.readLine()) == null)) {

			while (line.startsWith("template VertexDuplicationIndices")
					|| line.startsWith("template FVFData")) {
				line = skipTemplate();
				line = skipBlanks();
			}

			if (!line.startsWith("template")) {
				processLine(line);
			}

			for (Iterator iterator = boneListAux.iterator(); iterator.hasNext();) {
				Bone bone = (Bone) iterator.next();
				System.out.println(bone.name);
				System.out.println("index: " + bone.index);
				System.out.println("padre: " + bone.indexParent + "\n");

			}
		}
	}

	private void checkHeader(String line) throws Exception {

		if (!line.substring(0, 3).equals("xof")) {
			throw new Exception("It is not an x file..");
		}
		if (!line.substring(4, 6).equals(MINOR_VERSION)) {
			throw new Exception("It is not equal to minor version.");
		}
		if (!line.substring(6, 8).equals(MAJOR_VERSION)) {
			throw new Exception("It is not equal to major version.");
		}
		if (!line.substring(8, 11).equals(TYPE_FILE)) {
			throw new Exception("It is not a text format file.");
		}

	}

	private void processLine(String line, Object... objects) throws Exception {

		if (line != null && line.length() > 0) {
			DATA = TYPE_DATA.valueOf(line.substring(0, line.indexOf(" ")));
			Object[] params = new Object[1];
			if (objects.length > 0) {
				params = objects;
			}

			switch (DATA) {

			case Frame:
				processFrame(line, params[0]);
				break;
			case Mesh:
				processMesh();
				break;

			}
		}

	}

	private void processFrame(String line, Object parent) throws IOException,
			Exception {

		String[] tokens = line.split(" ");
		Bone bone = null;
		if (tokens.length == 3) {
			bone = new Bone(tokens[NAME_BONE]);
		} else {
			bone = new Bone("Bone_" + contFrames);
		}

		bone = processFrameTransformMatrix(bone);
		bone.index = contFrames++;
		indexParent++;

		line = skipBlanks();
		if (line.equals("}")) {
			line = skipBlanks();
		}

		boneList.add(bone);

		if (parent != null) {
			Bone boneTmp = (Bone) parent;
			bone.indexParent = boneTmp.index;
			if (!line.equals("}")) {
				processLine(line, bone);
			} else {
				while (line.equals("}")) {
					line = skipBlanks();
					boneListAux.add(boneList.pollLast());
					indexParent--;
				}
				processLine(line, boneList.get(indexParent - 1));
			}
		} else {
			processLine(line, bone);
		}

	}

	public void processMesh() throws Exception {

		String line = null;
		String[] data = null;
		Mesh mesh = new Mesh();
		line = skipBlanks();
		data = line.split(";");

		mesh.numVertices = Integer.parseInt(data[0]);
		mesh.index = contMeshes++;
		// indexParent++;

		mesh.vertexBuffer = new FloatDataBuffer(mesh.numVertices, 3);
		for (int i = 0; i < mesh.numVertices; i++) {
			line = skipBlanks();
			data = line.split(",");
			data = data[0].split(";");
			mesh.vertexBuffer.buffer.put(Float.parseFloat(data[0]));
			mesh.vertexBuffer.buffer.put(Float.parseFloat(data[1]));
			mesh.vertexBuffer.buffer.put(Float.parseFloat(data[2]));
		}

		line = skipBlanks();
		data = line.split(";");
		mesh.numFaces = Integer.parseInt(data[0]);
		mesh.faces = new IntegerDataBuffer(mesh.numFaces, 3);
		for (int i = 0; i < mesh.numFaces; i++) {
			line = skipBlanks();
			data = line.split(";");
			data = data[1].split(",");
			mesh.faces.buffer.put(Integer.parseInt(data[0]));
			mesh.faces.buffer.put(Integer.parseInt(data[1]));
			mesh.faces.buffer.put(Integer.parseInt(data[2]));
		}

		line = skipBlanks();

		if (line.startsWith(TYPE_DATA.MeshNormals.name())) {
			line = skipBlanks();
			data = line.split(";");
			mesh.numNormals = Integer.parseInt(data[0]);
			mesh.normalsBuffer = new FloatDataBuffer(mesh.numNormals, 3);
			for (int i = 0; i < mesh.numNormals; i++) {
				line = skipBlanks();
				data = line.split(",");
				data = data[0].split(";");
				mesh.normalsBuffer.buffer.put(Float.parseFloat(data[0]));
				mesh.normalsBuffer.buffer.put(Float.parseFloat(data[1]));
				mesh.normalsBuffer.buffer.put(Float.parseFloat(data[2]));
			}
		}

		line = skipBlanks();
		data = line.split(";");
		mesh.numFaces = Integer.parseInt(data[0]);
		mesh.normalsFaces = new IntegerDataBuffer(mesh.numFaces, 3);
		for (int i = 0; i < mesh.numFaces; i++) {
			line = skipBlanks();
			data = line.split(";");
			data = data[1].split(",");
			mesh.normalsFaces.buffer.put(Integer.parseInt(data[0]));
			mesh.normalsFaces.buffer.put(Integer.parseInt(data[1]));
			mesh.normalsFaces.buffer.put(Integer.parseInt(data[2]));
		}

		nextLine();
		line = skipBlanks();
		indexParent++;
		if (line.startsWith(TYPE_DATA.MeshMaterialList.name())) {
			line = skipBlanks();
			data = line.split(";");
			int numMat = Integer.parseInt(data[0]);
			line = skipBlanks();
			data = line.split(";");
			mesh.materials = new MaterialList(numMat, Integer.parseInt(data[0]));
			for (int i = 0; i < mesh.materials.numMaterialFaces; i++) {
				line = skipBlanks();
				data = line.split(",");
				if ((i + 1) == mesh.materials.numMaterialFaces) {
					data = data[0].split(";");
				}
				mesh.materials.materialFaces.put(Integer.parseInt(data[0]));
			}

			line = skipBlanks();

			while (line.startsWith(TYPE_DATA.Material.name())) {
				indexParent++;
				Material material = new Material();
				line = skipBlanks();
				data = line.split(";");
				material.colorRGB[0] = Float.parseFloat(data[0]);
				material.colorRGB[1] = Float.parseFloat(data[1]);
				material.colorRGB[2] = Float.parseFloat(data[2]);
				material.colorRGB[3] = Float.parseFloat(data[3]);
				line = skipBlanks();
				data = line.split(";");
				material.power = Float.parseFloat(data[0]);
				line = skipBlanks();
				data = line.split(";");
				material.emissive[0] = Float.parseFloat(data[0]);
				material.emissive[1] = Float.parseFloat(data[1]);
				material.emissive[2] = Float.parseFloat(data[2]);
				line = skipBlanks();
				data = line.split(";");
				material.specular[0] = Float.parseFloat(data[0]);
				material.specular[1] = Float.parseFloat(data[1]);
				material.specular[2] = Float.parseFloat(data[2]);

				mesh.materials.materials.add(material);
				line = nextLine();
				while (line.equals("}")) {
					indexParent--;
					line = nextLine();
				}

				line = skipBlanks();

				// line = skipBlankAndCloseBraces(line);
			}
		}

		if (line.startsWith(TYPE_DATA.VertexDuplicationIndices.name())) {
			line = skipTemplate();
		}

		nextLine();
		line = nextLine();
		while (line.equals("}")) {
			boneListAux.add(boneList.pollLast());
			indexParent--;
			line = nextLine();
		}

		entity.meshContainer.meshes.add(mesh);

		line = skipBlanks();

		processLine(line, boneList.get(indexParent - 1));

	}

	private Bone processFrameTransformMatrix(Bone bone) throws IOException {

		String line = null;
		String[] mat = null;

		line = skipBlanks();
		line = nextLine();

		mat = line.split(",");
		bone.matrixTransform = new Matrix(4, 4);

		bone.matrixTransform.setValue(0, 0, Double.valueOf(mat[0]));
		bone.matrixTransform.setValue(0, 1, Double.parseDouble(mat[1]));
		bone.matrixTransform.setValue(0, 2, Double.parseDouble(mat[2]));
		bone.matrixTransform.setValue(0, 3, Double.parseDouble(mat[3]));
		bone.matrixTransform.setValue(1, 0, Double.parseDouble(mat[4]));
		bone.matrixTransform.setValue(1, 1, Double.parseDouble(mat[5]));
		bone.matrixTransform.setValue(1, 2, Double.parseDouble(mat[6]));
		bone.matrixTransform.setValue(1, 3, Double.parseDouble(mat[7]));
		bone.matrixTransform.setValue(2, 0, Double.parseDouble(mat[8]));
		bone.matrixTransform.setValue(2, 1, Double.parseDouble(mat[9]));
		bone.matrixTransform.setValue(2, 2, Double.parseDouble(mat[10]));
		bone.matrixTransform.setValue(2, 3, Double.parseDouble(mat[11]));
		bone.matrixTransform.setValue(3, 0, Double.parseDouble(mat[12]));
		bone.matrixTransform.setValue(3, 1, Double.parseDouble(mat[13]));
		bone.matrixTransform.setValue(3, 2, Double.parseDouble(mat[14]));
		bone.matrixTransform.setValue(3, 3,
				Double.parseDouble(mat[15].substring(0, mat[15].length() - 2)));

		return bone;
	}

	public String skipBlanks() throws IOException {
		String line = null;
		while (!((line = br.readLine()) == null) && line.trim().equals("")) {
		}

		return line != null ? line.trim() : "";
	}

	public String skipTemplate() throws IOException {
		String line = null;
		while (!((line = br.readLine()) == null) && !line.trim().equals("}"))
			;

		return line.trim();
	}

	public String nextLine() throws IOException {
		return br.readLine().trim();
	}

	public String skipBlankAndCloseBraces(String line) throws IOException {
		while (line.trim().equals("") || line.trim().equals("}"))
			line = br.readLine();

		return line.trim();
	}

	@Override
	public Entity3D getEntity() {

		return entity;
	}

	public static void main(String[] args) {

		XFileLoader loader = new XFileLoader();
		try {
			loader.loadEntity("D:\\Temp\\prueba.x");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
