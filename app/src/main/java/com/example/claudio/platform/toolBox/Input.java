package com.example.claudio.platform.toolBox;

import android.view.MotionEvent;

import com.example.claudio.platform.entities.Button;
import com.example.claudio.platform.manager.ButtonsManager;
import com.example.claudio.platform.physicsEngine.Collision;

import java.util.List;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Input {

    public static final int LEFT    = 0;
    public static final int RIGHT   = 1;
    public static final int JUMP    = 2;

    private static boolean[] keys   = new boolean[3];

    public static void checkInput(float x, float y, int actionType){
        List<Button> buttons = ButtonsManager.getButtons();
        boolean found    = false;
        int     i       = 0;
        int     type    = -1;
        while(found == false && i < buttons.size()){
            if(Collision.testCollision(new Vector2f(x, y), buttons.get(i).getBoundingBox())){
                found = true;
                type = buttons.get(i).getType();
            }
            i++;
        }
        switch (actionType) {
            case MotionEvent.ACTION_DOWN:
                setKeyDown(type);
                break;
            case MotionEvent.ACTION_UP:
                setKeyUp(type);
                break;
        }
    }

    public static void setKeyDown(int type){
        switch (type) {
            case LEFT:
                keys[LEFT] = true;
                break;
            case RIGHT:
                keys[RIGHT] = true;
                break;
            case JUMP:
                keys[JUMP] = true;
                break;
        }

    }

    public static void setKeyUp(int key){
        switch (key) {
            case LEFT:
                keys[LEFT] = false;
                break;
            case RIGHT:
                keys[RIGHT] = false;
                break;
            case JUMP:
                keys[JUMP] = false;
                break;
        }
    }

    public static boolean isKeyDown(int key){
        if(keys[key] == true)
            return true;
        else {
            return false;
        }
    }

    public static boolean isKeyUp(int key){
        if(keys[key] == false)
            return true;
        else {
            return false;
        }
    }

}
