package com.cc.platform.terrains;

import android.opengl.GLES20;
import android.opengl.GLES30;

import com.cc.platform.renderEngine.Renderable;
import com.cc.platform.shaders.TileMapShader;
import com.cc.platform.tile.TileLevel;
import com.cc.platform.tile.Tileset;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio on 29/03/2017.
 */

public class TileMap implements Renderable {

    private int[] VAO, VBO, EBO, positionVBO, textureIndexVBO;

    private List<Tileset> tilesets = new ArrayList<>();
    private List<TileLevel> tileLevels = new ArrayList<>();
    private int tileCount = 0;

    public static Vector2f dimension;

    private TileMapShader shader;

    private float[] vertexArray = {
            // Positions
            64.0f,  0.0f, 0.0f, 1.0f, 0.0f, // Top Right
            64.0f, 64.0f, 0.0f, 1.0f, 1.0f, // Bottom Right
            0.0f, 64.0f, 0.0f, 0.0f, 1.0f, // Bottom Left
            0.0f,  0.0f, 0.0f, 0.0f, 0.0f  // Top Left
    };

    private int[] indexArray = {
            0, 1, 3, // First Triangle
            1, 2, 3  // Second Triangle
    };

    public TileMap(){
        VAO = new int[1];
        VBO = new int[1];
        EBO = new int[1];
        positionVBO = new int[1];
        textureIndexVBO = new int[1];
    }

    @Override
    public void render() {
        shader.start();

        bindAttribute();
        bindTexture();
        bindUniform();
        GLES30.glDrawElementsInstanced(GLES20.GL_TRIANGLES, indexArray.length, GLES20.GL_UNSIGNED_INT, 0, tileCount);
        unbindAttribute();

        shader.stop();
    }

    @Override
    public void handleInput() {

    }

    private void bindUniform() {
        shader.loadTilesetNumberOfRows(tilesets.get(0).getNumberOfRows());
        shader.loadTilesetNumberOfColumns(tilesets.get(0).getNumberOfColumns());
    }

    private void bindTexture() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tilesets.get(0).getTextureID());
    }

    private void bindAttribute() {
        GLES30.glBindVertexArray(VAO[0]);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glEnableVertexAttribArray(1);
        GLES30.glEnableVertexAttribArray(2);
        GLES30.glEnableVertexAttribArray(3);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, EBO[0]);
    }

    private void unbindAttribute() {
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
        GLES30.glDisableVertexAttribArray(2);
        GLES30.glDisableVertexAttribArray(3);
        GLES30.glBindVertexArray(0);
    }

    @Override
    public void update() {

    }

    @Override
    public void bindProjectionMatrix(float[] projectionMatrix) {
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    @Override
    public void bindViewMatrix(float[] viewMatrix){
        shader.start();
        shader.loadViewMatrix(viewMatrix);
        shader.stop();
    }

    public void bindBuffers(){
        GLES30.glGenVertexArrays(1, VAO, 0);
        GLES20.glGenBuffers(1, VBO, 0);
        GLES20.glGenBuffers(1, EBO, 0);
        GLES20.glGenBuffers(1, positionVBO, 0);
        GLES20.glGenBuffers(1, textureIndexVBO, 0);

        GLES30.glBindVertexArray(VAO[0]);

        // Bind vertex and texture buffer
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO[0]);
        ByteBuffer pointVBB = ByteBuffer.allocateDirect(vertexArray.length * 4);
        pointVBB.order(ByteOrder.nativeOrder());
        FloatBuffer vertexBuffer = pointVBB.asFloatBuffer();
        vertexBuffer.put(vertexArray);
        vertexBuffer.position(0);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexBuffer.capacity()*4,
                vertexBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 5*4,0);
        GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, 5*4,3*4);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        Util.checkError();

        // Bind textureIndexBuffer
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, textureIndexVBO[0]);
        IntBuffer textureIndexBuffer = IntBuffer.allocate(tileCount);
        for(TileLevel level : tileLevels){
            textureIndexBuffer.put(level.getTextureIndex());
        }
        textureIndexBuffer.position(0);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, textureIndexBuffer.capacity()*4,
                textureIndexBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glVertexAttribPointer(3,1,GLES20.GL_UNSIGNED_INT, false, 1*4,0);
        GLES30.glVertexAttribDivisor(3,1);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        // Bind position buffer
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, positionVBO[0]);
        FloatBuffer positionBuffer = FloatBuffer.allocate(tileCount*3);
        for(TileLevel level : tileLevels){
            positionBuffer.put(level.getPositions());
        }

        positionBuffer.position(0);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, positionBuffer.capacity()*4,
                positionBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glVertexAttribPointer(2,3,GLES20.GL_FLOAT, false, 3*4, 0);
        GLES30.glVertexAttribDivisor(2,1);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        // Bind index buffer
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, EBO[0]);
        ByteBuffer pointIBB = ByteBuffer.allocateDirect(indexArray.length * 4);
        pointIBB.order(ByteOrder.nativeOrder());
        IntBuffer indexBuffer = pointIBB.asIntBuffer();
        indexBuffer.put(indexArray);
        indexBuffer.position(0);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity() * 4,
                indexBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        Util.checkError();

        GLES30.glBindVertexArray(0);
    }

    public void setTileset(List<Tileset> tilesets){
        this.tilesets = tilesets;
    }

    public void setTileLevels(List<TileLevel> tileLevels){
        for(TileLevel level : tileLevels)
            tileCount += level.getTilesNumber();
        this.tileLevels = tileLevels;
        dimension = tileLevels.get(0).getDimension();
    }

    public TileLevel getTileLevel(int level){
        return tileLevels.get(level);
    }

    public Tileset getTileset(int set){
        return tilesets.get(set);
    }

    public void setShader(TileMapShader shader){
        this.shader = shader;
    }

}
