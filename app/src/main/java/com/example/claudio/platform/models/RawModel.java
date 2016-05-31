package com.example.claudio.platform.models;

/**
 * Created by Claudio on 30/05/2016.
 */
public class RawModel {

    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount){
        this.vaoID       = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID(){return vaoID;}

    public int getVertexCount(){return vertexCount;}

}
