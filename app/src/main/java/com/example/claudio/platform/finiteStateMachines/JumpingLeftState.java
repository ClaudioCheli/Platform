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
    private float jumpStartTime = 0;

    @Override
    public void enter(Player player) {
        player.setJumping(true);
        jumpStartTime = DisplayManager.getCurrentTime();
        jumpingLeftAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput(Player player) {
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
        if(!player.isJumping()){
            exit();
            return idleLeftState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        float horizontalVelocity    = 0;
        float verticalVelocity      = 0;
        if(buttonLeftPressed && player.getPosition().y > 100)
            horizontalVelocity = -player.SPEED * DisplayManager.getFrameTimeSeconds();
        if(player.getPosition().y > 0 && DisplayManager.getCurrentTime()-jumpStartTime < 300)
            verticalVelocity = -player.JUMP_SPEED * DisplayManager.getFrameTimeSeconds() * 2;
        player.updatePosition(new Vector2f(horizontalVelocity, verticalVelocity));
        jumpingLeftAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        jumpStartTime = 0;
        jumpingLeftAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return jumpingLeftAnimation.getCurrentID();
    }
}
