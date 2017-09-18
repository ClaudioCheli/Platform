package com.example.claudio.platform.finiteStateMachines;

import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.toolBox.Input;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 18/05/2017.
 */

public class RunningLeftState extends PlayerState {

    @Override
    public void enter(Player player) {
        runningLeftAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput(Player player) {
        if(Input.isKeyDown(Util.BUTTON_UP)){
            return jumpingLeftState;
        }
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            return null;
        }
        if(Input.isKeyDown(Util.BUTTON_RIGHT)){
            exit();
            return runningRightState;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP)){
            exit();
            return idleLeftState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        player.updatePosition(new Vector2f(-player.SPEED * DisplayManager.getFrameTimeSeconds(), 0));
        runningLeftAnimation.update(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        runningLeftAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return runningLeftAnimation.getCurrentID();
    }
}
