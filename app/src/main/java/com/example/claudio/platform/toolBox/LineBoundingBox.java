package com.example.claudio.platform.toolBox;

/**
 * Created by Claudio on 12/04/2017.
 */

public class LineBoundingBox implements BoundingBox {

    private Vector2f position;
    private Vector2f endpoints[] = new Vector2f[2];

    public LineBoundingBox(Vector2f position, Vector2f endpoints[]){
        this.position   = new Vector2f(position.x, position.y);
        this.endpoints[0]  = new Vector2f(endpoints[0].x, endpoints[0].y);
        this.endpoints[1]  = new Vector2f(endpoints[1].x, endpoints[1].y);
    }

    public void setPosition(Vector2f pos){
        position.x = pos.x;
        position.y = pos.y;
    }

    public Vector2f getPosition(){return position;}

}
