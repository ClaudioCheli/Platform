package com.example.claudio.platform.finiteStateMachines;

import android.util.Log;

import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.toolBox.Input;
import com.example.claudio.platform.toolBox.Util;

/**
 * Created by Claudio on 18/05/2017.
 */

public class IdleRightState extends PlayerState {

    @Override
    public void enter(Player player) {
        Log.i("states", "enter idleRightState");
        idleRightAnimation.start(DisplayManager.getCurrentTime());
    }

    @Override
    public PlayerState handleInput(Player player) {
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            Log.i("states", "idleRightState return runningLeftState");
            exit();
            return runningLeftState;
        }
        if(Input.isKeyDown(Util.BUTTON_RIGHT)){
            exit();
            return runningRightState;
        }
        if(Input.isKeyDown(Util.BUTTON_UP)){
            exit();
            return jumpingRightState;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP)){
            return this;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        Log.i("states", "update idleRightState");
        idleRightAnimation.update(DisplayManager.getCurrentTime());
    }

    @Override
    public void exit() {
        Log.i("states", "exit idleRightState");
        idleRightAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return idleRightAnimation.getCurrentID();
    }
}
