package com.example.claudio.platform.entities;


import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.claudio.platform.animations.Animation;
import com.example.claudio.platform.finiteStateMachines.PlayerState;
import com.example.claudio.platform.shaders.PlayerShader;
import com.example.claudio.platform.shaders.Shader;
import com.example.claudio.platform.tile.Tile;
import com.example.claudio.platform.tile.Tileset;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public class Player extends Entity{

    private int[] VAO, VBO, EBO;
    private PlayerShader shader;
    private Tile tile;
    private List<Tileset> tilesets;
    private PlayerState state;

    public static final float SPEED = 300;
    public static final float JUMP_SPEED = 700;

    public Player(){
        VAO = new int[1];
        VBO = new int[1];
        EBO = new int[1];
    }

    @Override
    public void render() {
        shader.start();

        bindAttribute();
        bindTexture();
        bindUniform();
        GLES30.glDrawElements(GLES20.GL_TRIANGLES, tile.getIndexCount(), GLES20.GL_UNSIGNED_INT, 0);
        unbindAttribute();

        shader.stop();
    }

    @Override
    public void handleInput(){
        PlayerState newState = state.handleInput();
        if(newState != null){
            state = newState;
            state.enter();
        }
    }

    @Override
    public void update() {
        state.update(this);
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

    @Override
    public void bindBuffers() {
        GLES30.glGenVertexArrays(1, VAO, 0);
        GLES20.glGenBuffers(1, VBO, 0);
        GLES20.glGenBuffers(1, EBO, 0);

        GLES30.glBindVertexArray(VAO[0]);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, VBO[0]);
        FloatBuffer vertexBuffer = tile.getVertexBuffer();
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexBuffer.capacity() * 4,
                vertexBuffer, GLES20.GL_STATIC_DRAW);
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 5*4,0);
        GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, 5*4,3*4);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, EBO[0]);
        IntBuffer indexBuffer = tile.getIndexBuffer();
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, indexBuffer.capacity() * 4,
                indexBuffer, GLES20.GL_STATIC_DRAW);
        GLES30.glBindVertexArray(0);
    }

    @Override
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void setTileset(List<Tileset> tilesets) {
        this.tilesets = tilesets;
    }

    @Override
    public void setAnimation(List<Animation> animations) {
        PlayerState.setAnimations(animations);
        state = PlayerState.idleRightState;
        state.enter();
    }

    @Override
    public void setType(int type){}

    @Override
    public void setShader(Shader shader) {
        this.shader = (PlayerShader) shader;
    }

    public PlayerShader getShader(){return shader;}

    public int getVAO(){return VAO[0];}

    public void updatePosition(Vector2f position){
        increasePosition(new Vector3f(position.x,position.y,0.0f));
    }

    public Vector2f getPosition(){
        return new Vector2f(tile.getPosition().x, tile.getPosition().y);
    }

    public void setPosition(Vector2f position){
        tile.setPosition(new Vector3f(position.x, position.y, 0.0f));
    }

    public void increasePosition(Vector3f delta){
        tile.increasePosition(delta);
        }

    private void bindUniform(){
        shader.loadModelMatrix(tile.getModelMatrix());
        shader.loadTilesetNumberOfRows(tilesets.get(0).getNumberOfRows());
        shader.loadTilesetNumberOfColumns(tilesets.get(0).getNumberOfColumns());
        shader.loadTextureIndex(state.getAnimationID()+1);
    }

    private void bindTexture(){
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tilesets.get(0).getTextureID());
    }

    private void bindAttribute(){
        GLES30.glBindVertexArray(VAO[0]);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glEnableVertexAttribArray(1);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, EBO[0]);
    }

    private void unbindAttribute(){
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
        GLES30.glBindVertexArray(0);
    }

}
