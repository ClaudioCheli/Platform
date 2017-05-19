package com.example.claudio.platform.finiteStateMachines;

import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.toolBox.Input;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 18/05/2017.
 */

public class JumpingRightState extends PlayerState {

    private boolean buttonRightPressed = false;

    @Override
    public void enter() {
        jumpingRightAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput() {
        if(Input.isKeyDown(Util.BUTTON_RIGHT)){
            buttonRightPressed = true;
        }else if(Input.isKeyUp(Util.BUTTON_RIGHT)){
            buttonRightPressed = false;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP)){
            exit();
            return idleRightState;
        }
        if(Input.isKeyUp(Util.BUTTON_UP)){
            exit();
            return idleRightState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        float horizontalVelocity = 0;
        if(buttonRightPressed)
            horizontalVelocity = player.SPEED * DisplayManager.getFrameTimeSeconds();
        player.updatePosition(new Vector2f(horizontalVelocity, -player.JUMP_SPEED * DisplayManager.getFrameTimeSeconds() * 2));
        jumpingRightAnimation.update(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        jumpingRightAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return jumpingRightAnimation.getCurrentID();
    }
}
