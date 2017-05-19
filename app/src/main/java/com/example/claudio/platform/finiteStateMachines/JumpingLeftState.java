package com.example.claudio.platform.finiteStateMachines;

import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.toolBox.Input;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 19/05/2017.
 */

public class JumpingLeftState extends PlayerState {

    private boolean buttonLeftPressed = false;

    @Override
    public void enter() {
        jumpingLeftAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput() {
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            buttonLeftPressed = true;
        }else if(Input.isKeyUp(Util.BUTTON_LEFT)){
            buttonLeftPressed = false;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP)){
            exit();
            return idleLeftState;
        }
        if(Input.isKeyUp(Util.BUTTON_UP)){
            exit();
            return idleLeftState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        float horizontalVelocity = 0;
        if(buttonLeftPressed)
            horizontalVelocity = -player.SPEED * DisplayManager.getFrameTimeSeconds();
        player.updatePosition(new Vector2f(horizontalVelocity, -player.JUMP_SPEED * DisplayManager.getFrameTimeSeconds() * 2));
        jumpingLeftAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        jumpingLeftAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return jumpingLeftAnimation.getCurrentID();
    }
}
