package com.example.claudio.platform.entities;

import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

/**
 * Created by clasb on 28/05/2016.
 */
public class Camera {

    private Vector3f position    = new Vector3f(0, 0, 0);
    private Vector2f topLeft     = DisplayManager.TOP_LEFT;
    private Vector2f bottomRight = DisplayManager.BOTTOM_RIGHT;
    private float pitch;
    private float yaw;
    private float roll;

    public Vector3f getPosition(){
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
