package com.cc.platform.text;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Claudio on 21/09/2017.
 */

public class TextModel {

    private int[] VAO = new int[1];
    private int[] VBO = new int[1];
    private int[] EBO = new int[1];

    final static int POSITION_CNT_2D = 2;              // Number of Components in Vertex Position for 2D
    final static int POSITION_CNT_3D = 3;              // Number of Components in Vertex Position for 3D
    final static int COLOR_CNT = 4;                    // Number of Components in Vertex Color
    final static int TEXCOORD_CNT = 2;                 // Number of Components in Vertex Texture Coords

    final static int INDEX_SIZE = Short.SIZE / 8;      // Index Byte Size (Short.SIZE = bits)


    public final int positionCnt;                      // Number of Position Components (2=2D, 3=3D)
    public final int vertexStride;                     // Vertex Stride (Element Size of a Single Vertex)
    public final int vertexSize;                       // Bytesize of a Single Vertex
    final FloatBuffer vertices;                          // Vertex Buffer
    final ShortBuffer indices;                         // Index Buffer
    final int[] tmpBuffer;                             // Temp Buffer for Vertex Conversion


    public TextModel(int maxVertices, int maxIndices) {
        this.positionCnt = POSITION_CNT_2D;
        this.vertexStride = this.positionCnt + TEXCOORD_CNT;
        this.vertexSize = this.vertexStride * 4;

        ByteBuffer buffer = ByteBuffer.allocateDirect(maxVertices * vertexSize);
        buffer.order(ByteOrder.nativeOrder());
        this.vertices = buffer.asFloatBuffer();

        buffer = ByteBuffer.allocateDirect(maxIndices * INDEX_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        this.indices = buffer.asShortBuffer();

        this.tmpBuffer = new int[maxVertices * vertexSize / 4];

    }

    public void bindBuffers() {
        GLES30.glGenVertexArrays(1, VAO, 0);
        GLES20.glGenBuffers(1, VBO, 0);
        GLES20.glGenBuffers(1, EBO, 0);

        GLES30.glBindVertexArray(VAO[0]);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO[0]);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertices.capacity()*4,
                vertices, GLES20.GL_DYNAMIC_DRAW);
        GLES20.glVertexAttribPointer(0, positionCnt, GLES20.GL_FLOAT, false, vertexSize, 0);
        GLES20.glVertexAttribPointer(1, TEXCOORD_CNT, GLES20.GL_FLOAT, false, vertexSize, positionCnt*4);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, EBO[0]);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indices.capacity()*INDEX_SIZE,
                indices, GLES20.GL_STATIC_DRAW);

        GLES30.glBindVertexArray(0);
    }

    public void setVertices(float[] vertices) {
        this.vertices.clear();
        this.vertices.put(vertices);
        this.vertices.position(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO[0]);
        GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER, 0, this.vertices.capacity()*4, this.vertices);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }

    public void setIndices(short[] indices) {
        this.indices.clear();
        this.indices.put(indices);
        this.indices.position(0);
    }

    public void draw(int indexCount) {
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexCount, GLES20.GL_UNSIGNED_SHORT, 0);
    }

    public void bind() {
        GLES30.glBindVertexArray(VAO[0]);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glEnableVertexAttribArray(1);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, EBO[0]);
    }

    public void unbind() {
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
        GLES30.glBindVertexArray(0);
    }


}
