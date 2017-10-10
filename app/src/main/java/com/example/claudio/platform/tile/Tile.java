package com.example.claudio.platform.tile;

import android.opengl.Matrix;

import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by Claudio on 23/03/2017.
 */

public class Tile {

    private Vector2f dimension = new Vector2f(1,1);
    private Vector3f position = new Vector3f(0,0,0);
    private float rotationAngle = 0;
    private Vector3f rotationAxis = new Vector3f(0,0,1);
    private Vector3f scale = new Vector3f(1,1,1);
    private float[] modelMatrix = new float[16];
    private static final int VERTEX_COUNT = 4;

    private float[] vertex = {
            1.0f,  0.0f, 0.0f, // Top Right
            1.0f, 1.0f, 0.0f, // Bottom Right
            0.0f, 1.0f, 0.0f, // Bottom Left
            0.0f,  0.0f, 0.0f
    } ;

    private float[] texture = {
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f

    };

    private int[] index = {
            0, 1, 3,
            1,2,3
    };

    public Tile(Vector2f dim){
        dimension.x = dim.x;
        dimension.y = dim.y;
        Matrix.setIdentityM(modelMatrix, 0);
        for(int i=0; i<vertex.length; i+=3){
            vertex[i]       *= dim.x;
            vertex[i + 1]   *= dim.y;
        }
    }

    public void resetModelMatrix(){
        Matrix.setIdentityM(modelMatrix, 0);
    }

    public FloatBuffer getVertexBuffer(){
        float[] vertexArray = getVertexArray();
        ByteBuffer pointVBB = ByteBuffer.allocateDirect(vertexArray.length * 4);
        pointVBB.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = pointVBB.asFloatBuffer();
        buffer.put(vertexArray);
        buffer.position(0);
        return buffer;
    }

    public IntBuffer getIndexBuffer() {
        ByteBuffer pointVBB = ByteBuffer.allocateDirect(index.length * 4);
        pointVBB.order(ByteOrder.nativeOrder());
        IntBuffer buffer = pointVBB.asIntBuffer();
        buffer.put(index);
        buffer.position(0);
        return buffer;
    }

    public float[] getVertexArray(){
        float[] vertexArray = new float[vertex.length + texture.length];
        int ver = 0;
        int tex = 0;
        int count = 0;
        for(int i=0; i<VERTEX_COUNT; i++){
            vertexArray[count] = vertex[ver];  ver++; count++;
            vertexArray[count] = vertex[ver];  ver++; count++;
            vertexArray[count] = vertex[ver];  ver++; count++;
            vertexArray[count] = texture[tex]; tex++; count++;
            vertexArray[count] = texture[tex]; tex++; count++;
        }

        return vertexArray;
    }

    public int[] getIndexArray(){return index;}
    public int getIndexCount(){return index.length;}
    public Vector2f getDimension(){return dimension;}
    public float[] getModelMatrix(){return modelMatrix;}
    public Vector3f getPosition(){return position;}

    public void increasePosition(Vector3f pos){
        position.x += pos.x; position.y += pos.y; position.z += pos.z;
        Matrix.translateM(modelMatrix, 0, pos.x, pos.y, pos.z);
    }

    public void setPosition(Vector3f pos){
        Matrix.translateM(modelMatrix, 0, pos.x-position.x, pos.y-position.y, position.z);
        position.x = pos.x; position.y = pos.y; position.z = pos.z;
    }

    public void setRotation(Vector3f rotationAxis, float rotationAngle){
        this.rotationAxis.x = rotationAxis.x;
        this.rotationAxis.y = rotationAxis.y;
        this.rotationAxis.z = rotationAxis.z;
        this.rotationAngle = rotationAngle;
        Matrix.rotateM(modelMatrix, 0, rotationAngle,
                rotationAxis.x, rotationAxis.y, rotationAxis.z);
    }

    public void setScale(Vector3f scale){
        this.scale.x = scale.x;
        this.scale.y = scale.y;
        this.scale.z = scale.z;
        Matrix.scaleM(modelMatrix, 0, scale.x, scale.y, scale.z);
    }

}
