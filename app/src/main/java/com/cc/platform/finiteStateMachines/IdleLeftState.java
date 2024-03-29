package com.cc.platform.finiteStateMachines;

import com.cc.platform.entities.Player;
import com.cc.platform.time.Time;
import com.cc.platform.toolBox.Input;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 18/05/2017.
 */

public class IdleLeftState extends PlayerState {

    @Override
    public void enter(Player player) {
        idleLeftAnimation.start(Time.getCurrentTime());
        player.getPhysicModel().setTargetSpeed(new Vector2f(0,0));
    }

    @Override
    public PlayerState handleInput(Player player) {
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            exit();
            return runningLeftState;
        }
        if(Input.isKeyDown(Util.BUTTON_RIGHT)){
            exit();
            return runningRightState;
        }
        if(Input.isKeyDown(Util.BUTTON_UP)){
            exit();
            return jumpingLeftState;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP)){
            return this;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        idleLeftAnimation.update(Time.getCurrentTime());
        player.getPhysicModel().setTargetSpeed(new Vector2f(0,0));
    }

    @Override
    public void exit() {
        idleLeftAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return idleLeftAnimation.getCurrentID();
    }
}
