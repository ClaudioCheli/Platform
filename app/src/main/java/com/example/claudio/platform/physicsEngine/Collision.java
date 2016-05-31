package com.example.claudio.platform.physicsEngine;

import com.example.claudio.platform.toolBox.SquareBoundingBox;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Collision {

    public static boolean testCollision(SquareBoundingBox B1, SquareBoundingBox B2){
        if(B1.getBottomRight().x >= B2.getTopLeft().x && B1.getTopLeft().x >= B2.getBottomRight().x &&
                B1.getTopLeft().y >= B2.getBottomRight().y && B1.getBottomRight().y <= B2.getTopLeft().y){
            return true;
        }
        return false;
    }

    public static boolean testCollision(Vector2f in, SquareBoundingBox B){
        if(in.x >= B.getTopLeft().x && in.x <= B.getBottomRight().x &&
                in.y <= B.getTopLeft().y && in.y >= B.getBottomRight().y){
            return true;
        }
        return false;
    }

}
