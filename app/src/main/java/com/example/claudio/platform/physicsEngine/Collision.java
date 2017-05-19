package com.example.claudio.platform.physicsEngine;

import android.util.Log;

import com.example.claudio.platform.toolBox.BoundingBox;
import com.example.claudio.platform.toolBox.LineBoundingBox;
import com.example.claudio.platform.toolBox.SquareBoundingBox;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Collision {

    public static boolean checkCollision(Vector2f point, BoundingBox box){
        if(box instanceof SquareBoundingBox){
            return checkCollision(point, (SquareBoundingBox) box);
        }
        return false;
    }

    private static boolean checkCollision(Vector2f point, SquareBoundingBox box){
        Vector2f boxPosition    = box.getPosition();
        Vector2f boxDimension   = box.getDimension();
        if(point.x >= boxPosition.x && point.x <= (boxPosition.x+boxDimension.x)){
            if(point.y >= boxPosition.y && point.y <= (boxPosition.y+boxDimension.y)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean checkBottomCollision(Vector2f point, BoundingBox box){
        if(box instanceof SquareBoundingBox){
            return checkBottomCollision(point, (SquareBoundingBox) box);
        }else if(box instanceof LineBoundingBox){
            return checkBottomCollision(point, (LineBoundingBox) box);
        }
        return false;
    }

    private static boolean checkBottomCollision(Vector2f point, SquareBoundingBox box){
        Vector2f boxPosition = box.getPosition();
        Vector2f boxDimension = box.getDimension();
        if( point.x <= (boxPosition.x+boxDimension.x) && point.x >= boxPosition.x){
            //Log.i("physic","point: " + point.x + ", " + point.y + " box: " + boxPosition.x + ", " + boxPosition.y);
            if(point.y >= boxPosition.y)
                return true;
            return false;
        }
        return false;
    }

    private static boolean checkBottomCollision(Vector2f point, LineBoundingBox box){
        Vector2f endpoints[] = box.getEndpoints();
        Vector2f a = endpoints[0];
        Vector2f b = endpoints[1];
        Log.i("physic", "point: " + point.x + ", " + point.y + " box: " + a.x + ", " + b.x);
        if(point.x >= a.x && point.x <= b.x) {
            float yLine = (b.y * (point.x - a.x) - a.y * (point.x - b.x)) / (b.x - a.x);
            Log.i("physic", "point: " + point.x + ", " + point.y + " box: " + yLine);
            if (yLine <= point.y) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkRightCollision(){
        return false;
    }


}
