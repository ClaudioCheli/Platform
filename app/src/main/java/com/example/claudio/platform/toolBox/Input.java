package com.example.claudio.platform.toolBox;


import android.util.Log;
import android.view.MotionEvent;

import com.example.claudio.platform.entities.Button;
import com.example.claudio.platform.physicsEngine.Collision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Input {

    private static Map<Integer, Boolean> keys = new HashMap<>();
    private static Map<Integer, Vector2f> activePointers = new HashMap<>();
    private static Map<Integer, Integer> pointerToButton = new HashMap<>();


    public static void checkInput(MotionEvent event, List<Button> buttons){
        Log.i("input", "check input");
        int pointerIndex    = event.getActionIndex();
        int pointerId       = event.getPointerId(pointerIndex);
        int maskedAction    = event.getActionMasked();
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);

        switch (maskedAction){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                boolean found    = false;
                int     i       = 0;
                int     buttonType    = -1;
                while(found == false && i < buttons.size()){
                    if(Collision.checkCollision(new Vector2f(x, y), buttons.get(i).getBoundingBox())){
                        found = true;
                        buttonType = buttons.get(i).getType();
                        activePointers.put(pointerId, new Vector2f(x, y));
                        pointerToButton.put(pointerId, buttonType);
                        setKeyDown(buttonType);
                    }
                    i++;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                activePointers.remove(pointerId);
                setKeyUp(pointerToButton.get(pointerId));
                pointerToButton.remove(pointerId);
                break;
        }
    }

    public static void setKeyDown(int keyType){
        switch (keyType) {
            case Util.BUTTON_LEFT:
                keys.put(Util.BUTTON_LEFT, true);
                break;
            case Util.BUTTON_RIGHT:
                keys.put(Util.BUTTON_RIGHT, true);
                break;
            case Util.BUTTON_UP:
                keys.put(Util.BUTTON_UP, true);
                break;
        }

    }

    public static void setKeyUp(int keyType){
        switch (keyType) {
            case Util.BUTTON_LEFT:
                keys.put(Util.BUTTON_LEFT, false);
                break;
            case Util.BUTTON_RIGHT:
                keys.put(Util.BUTTON_RIGHT, false);
                break;
            case Util.BUTTON_UP:
                keys.put(Util.BUTTON_UP, false);
                break;
        }
    }

    public static boolean isKeyDown(int keyType){
        if(keys.containsKey(keyType)){
            if(keys.get(keyType) == true)
                return true;
            else {
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean isKeyUp(int keyType){
        if(keys.containsKey(keyType)) {
            if (keys.get(keyType) == false)
                return true;
            else {
                return false;
            }
        }else{
            return false;
        }
    }

}
