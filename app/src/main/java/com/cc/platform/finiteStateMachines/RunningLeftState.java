package com.cc.platform.finiteStateMachines;

import com.cc.platform.entities.Player;
import com.cc.platform.physicsEngine.Physics;
import com.cc.platform.time.Time;
import com.cc.platform.toolBox.Input;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 18/05/2017.
 */

public class RunningLeftState extends PlayerState {

    @Override
    public void enter(Player player) {
        runningLeftAnimation.start(Time.getCurrentTime());
        player.getPhysicModel().setTargetSpeed(new Vector2f(-Physics.HORIZONTAL_FORCE, player.getPhysicModel().getTargetSpeed().y));
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
        player.getPhysicModel().setTargetSpeed(new Vector2f(-Physics.HORIZONTAL_FORCE, player.getPhysicModel().getTargetSpeed().y));
        //player.updatePosition(new Vector2f(-player.SPEED * Time.getFrameTimeSeconds(), 0));
        runningLeftAnimation.update(Time.getCurrentTime());
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
