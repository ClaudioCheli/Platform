package com.cc.platform.toolBox;


import android.view.MotionEvent;

import com.cc.platform.entities.Button;
import com.cc.platform.physicsEngine.Collision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Input {

    private static final boolean[] keys = new boolean[3];

    private static Map<Integer, Vector2f> activePointers = new HashMap<>();
    private static Map<Integer, Integer> pointerToButton = new HashMap<>();

    public static void checkInput(MotionEvent event, List<Button> buttons) {
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int maskedAction = event.getActionMasked();
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                boolean found = false;
                int i = 0;
                int buttonType;
                while (!found && i < buttons.size()) {
                    if (Collision.checkCollision(new Vector2f(x, y), buttons.get(i).getBoundingBox())) {
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
                if (activePointers.containsKey(pointerId)) {
                    activePointers.remove(pointerId);
                    setKeyUp(pointerToButton.get(pointerId));
                    pointerToButton.remove(pointerId);
                }
                break;
        }
    }

    private static void setKeyDown(int keyType) {
        if (keyType < 0 || keyType > keys.length) {
            return;
        }
        keys[keyType] = true;
    }

    private static void setKeyUp(int keyType) {
        if (keyType < 0 || keyType > keys.length) {
            return;
        }
        keys[keyType] = false;
    }

    public static boolean isKeyDown(int keyType) {
        if (keyType < 0 || keyType > keys.length) {
            return false;
        }
        return keys[keyType];
    }

    public static boolean isKeyUp(int keyType) {
        if (keyType < 0 || keyType > keys.length) {
            return false;
        }
        return !keys[keyType];
    }

}
