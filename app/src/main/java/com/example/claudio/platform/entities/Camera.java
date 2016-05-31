package com.example.claudio.platform.entities;

import com.example.claudio.platform.animations.PlayerAnimation;
import com.example.claudio.platform.manager.ButtonsManager;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.manager.PlayerManager;
import com.example.claudio.platform.manager.TerrainManager;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.Manager;
import com.example.claudio.platform.toolBox.SquareBoundingBox;
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

    private PlayerManager       player;
    private ButtonsManager      button;
    private TerrainManager      terrain;
    private SquareBoundingBox   boundingBox;

    public Camera(Manager player, Manager button, Manager terrain, Loader loader){
        this.player     = (PlayerManager)   player;
        this.button     = (ButtonsManager)  button;
        this.terrain    = (TerrainManager)  terrain;
        boundingBox     = new SquareBoundingBox(loader, new Vector2f(bottomRight.x-topLeft.x, topLeft.y-bottomRight.y),
                                position, true, true);
    }

    public void update(){
        if(player.getState() == PlayerAnimation.RUN){
            move(player.getDirection());
        }
    }

    public void move(int direction){
        if(direction == PlayerAnimation.DX){
            position.x    += 0.01f;
            topLeft.x 	  += 0.01f;
            bottomRight.x += 0.01f;
        }else if(direction == PlayerAnimation.SX){
            position.x 	  -= 0.01f;
            topLeft.x	  -= 0.01f;
            bottomRight.x -= 0.01f;
        }

        button.setState(direction);
        terrain.setState(direction);
    }

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

    public Vector2f getTopLeft(){
        return topLeft;
    }

    public Vector2f getBottomRight(){
        return bottomRight;
    }

    public SquareBoundingBox getBoundingBox(){
        return boundingBox;
    }
}
