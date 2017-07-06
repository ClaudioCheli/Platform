package com.example.claudio.platform.toolBox;

public class SquareBoundingBox implements BoundingBox{

    private Vector2f position;
    private Vector2f dimension;

    public SquareBoundingBox(Vector2f position, Vector2f dimension){
        this.position   = new Vector2f(position.x, position.y);
        this.dimension  = new Vector2f(dimension.x, dimension.y);
    }

    @Override
    public void setPosition(Vector2f pos){
        position.x = pos.x;
        position.y = pos.y;
    }

    @Override
    public Vector2f getPosition(){
        return new Vector2f(position);
    }

    @Override
    public Vector2f[] getEndpoints() {
        Vector2f[] endpoints = new Vector2f[2];
        endpoints[0] = new Vector2f(position.x, position.y);
        endpoints[1] = new Vector2f(position.x+dimension.x, position.y);
        return endpoints;
    }

    public Vector2f getDimension(){
        return new Vector2f(dimension);
    }

}