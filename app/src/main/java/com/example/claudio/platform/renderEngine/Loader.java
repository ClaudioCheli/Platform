package com.example.claudio.platform.renderEngine;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.claudio.platform.models.RawModel;
import com.example.claudio.platform.textures.Texture;

public class Loader {
	
	private static final int INT_SIZE = 4;
	private static final int FLOAT_SIZE = 4;
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	public RawModel loadToVAO(float[] positions, float[] textureCoords, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		storeDataInAttributeList(1, 2, textureCoords);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	public RawModel loadToVAO(float[] positions, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttributeList(0, 3, positions);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}
	
	private int createVAO(){
		int[] vaoID = new int[1];
		GLES30.glGenVertexArrays(1,vaoID,0);
		vaos.add(vaoID[0]);
		GLES30.glBindVertexArray(vaoID[0]);
		return vaoID[0];
	}
	
	private void bindIndicesBuffer(int[] indices){
		int[] vboID = new int[1];
		GLES20.glGenBuffers(1, vboID,0);
		vbos.add(vboID[0]);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, vboID[0]);
		IntBuffer buffer = storeDataInIntBuffer(indices);
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffer.capacity() * INT_SIZE,
									buffer, GLES20.GL_STATIC_DRAW);
	}
	
	private void storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
		int[] vboID = new int[1];
		GLES20.glGenBuffers(1, vboID,0);
		vbos.add(vboID[0]);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboID[0]);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.capacity() * FLOAT_SIZE,
									buffer, GLES20.GL_STATIC_DRAW);
		GLES20.glVertexAttribPointer(attributeNumber, coordinateSize, GLES20.GL_FLOAT, false, 0,0);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
	}
	
	public int loadTexture(int fileName){
		int t = Texture.LoadTexture(fileName);
		textures.add(t);
		return t;
	}
	
	/*public void cleanUp(){
		int[] vec = new int[1];
		for(int i=0; i< vaos.size(); i++){
			vec[0] = (int)vaos.get(i);
			GLES30.glDeleteVertexArrays(1, vec, 0);
			GLES30.glDeleteVertexArrays(1,vec ,0);
		}
		for(int vbo:vbos){
			GLES20.glDeleteBuffers(1,vbo,0);
		}
		for(int texture:textures){
			GL11.glDeleteTextures(texture);
		}
	}*/
	
	private void unbindVAO(){
		GLES30.glBindVertexArray(0);
	}
	
	
	private IntBuffer storeDataInIntBuffer(int[] data){
		ByteBuffer pointVBB = ByteBuffer.allocateDirect(data.length * 4);
		pointVBB.order(ByteOrder.nativeOrder());
		IntBuffer buffer = pointVBB.asIntBuffer();
		buffer.put(data);
		buffer.position(0);
		return buffer;
	}
	
	private FloatBuffer storeDataInFloatBuffer(float[] data){
		ByteBuffer pointVBB = ByteBuffer.allocateDirect(data.length * 4);
		pointVBB.order(ByteOrder.nativeOrder());
		FloatBuffer buffer = pointVBB.asFloatBuffer();
		buffer.put(data);
		buffer.position(0);
		return buffer;
	}

}
