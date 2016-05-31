package com.example.claudio.platform.entities;

import com.example.claudio.platform.models.TexturedModel;
import com.example.claudio.platform.toolBox.SquareBoundingBox;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

/**
 * Created by Claudio on 30/05/2016.
 */
public abstract class Entity {

    protected TexturedModel model;
    protected Vector3f position;
    protected Vector3f rotation;
    protected Vector3f scale;
    protected Vector2f dimension;

    protected int type;

    protected SquareBoundingBox boundingBox;

    protected int textureIndex = 0;

    public void setEntity(TexturedModel model, Vector3f position, Vector2f dimension
            , Vector3f rotation, Vector3f scale){
        this.model      = model;
        this.position   = position;
        this.dimension  = dimension;
        this.rotation   = rotation;
        this.scale      = scale;
    }

    public void setEntity(TexturedModel model, int index, Vector3f position, Vector2f dimension,
                          Vector3f rotation, Vector3f scale){
        this.model = model;
        this.textureIndex = index;
        this.position = position;
        this.dimension = dimension;
        this.rotation = rotation;
        this.scale = scale;
    }

    protected float findMinX(float[] vertices){
        float min = 100;
        for(int i=0; i<vertices.length; i+=3){
            if(min > vertices[i])
                min = vertices[i];
        }
        return min;
    }

    protected float findMinY(float[] vertices){
        float min = 100;
        for(int i=1; i<vertices.length; i+=3){
            if(min > vertices[i])
                min = vertices[i];
        }
        return min;
    }

    protected float findMaxX(float[] vertices){
        float max = -100;
        for(int i=0; i<vertices.length; i+=3){
            if(max < vertices[i])
                max = vertices[i];
        }
        return max;
    }

    protected float findMaxY(float[] vertices){
        float max = -100;
        for(int i=1; i<vertices.length; i+=3){
            if(max < vertices[i])
                max = vertices[i];
        }
        return max;
    }

    public float getTextureXOffset(){
        int column = textureIndex % model.getTexture().getNumberOfRows();
        return (float) column / (float) model.getTexture().getNumberOfRows();
    }

    public float getTextureYOffset(){
        int row = textureIndex / model.getTexture().getNumberOfRows();
        return (float) row / (float) model.getTexture().getNumberOfRows();
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
        boundingBox.update(new Vector2f(dx, dy));
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotation.x += dx;
        this.rotation.y += dy;
        this.rotation.z += dz;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public SquareBoundingBox getBoundingBox(){
        return boundingBox;
    }

    public void setIndex(int index) {
        this.textureIndex = index;
    }

    public void setType(int type){this.type = type;}

    public int getType(){return type;}

    public Vector2f getDimension() {
        return dimension;
    }

    public float getSize() {
        return getBoundingBox().getBottomRight().x - getBoundingBox().getTopLeft().x;
    }

}
