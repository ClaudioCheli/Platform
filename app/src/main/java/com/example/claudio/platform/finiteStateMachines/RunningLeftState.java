package com.example.claudio.platform.finiteStateMachines;

import android.util.Log;

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
        Log.i("states", "enter RunningLeftState");
        runningLeftAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput(Player player) {
        if(Input.isKeyDown(Util.BUTTON_UP)){
            return jumpingLeftState;
        }
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            Log.i("states", "RunningLeftState return null");
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
        Log.i("states", "update RunningLeftState");
        player.updatePosition(new Vector2f(-player.SPEED * DisplayManager.getFrameTimeSeconds(), 0));
        runningLeftAnimation.update(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        Log.i("states", "update RunningLeftState");
        runningLeftAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return runningLeftAnimation.getCurrentID();
    }
}
