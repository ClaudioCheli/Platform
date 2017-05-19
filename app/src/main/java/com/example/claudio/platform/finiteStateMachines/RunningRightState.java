package com.example.claudio.platform.finiteStateMachines;

import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.toolBox.Input;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;


/**
 * Created by Claudio on 18/05/2017.
 */

public class RunningRightState extends PlayerState {

    @Override
    public void enter() {
        runningRightAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput() {
        if(Input.isKeyDown(Util.BUTTON_UP)){
            exit();
            return jumpingRightState;
        }
        if(Input.isKeyDown(Util.BUTTON_RIGHT)){
            return null;
        }
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            exit();
            return runningLeftState;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP)){
            exit();
            return idleRightState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        player.updatePosition(new Vector2f(player.SPEED * DisplayManager.getFrameTimeSeconds(), 0));
        runningRightAnimation.update(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        runningRightAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return runningRightAnimation.getCurrentID();
    }
}
