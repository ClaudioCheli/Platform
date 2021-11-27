package com.cc.platform.finiteStateMachines;

import com.cc.platform.entities.Player;
import com.cc.platform.physicsEngine.Physics;
import com.cc.platform.time.Time;
import com.cc.platform.time.Timer;
import com.cc.platform.toolBox.Input;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 19/05/2017.
 */

public class JumpingLeftState extends PlayerState {

    private boolean buttonLeftPressed   = false;
    private boolean buttonRightPressed  = false;
    private Timer timer;

    @Override
    public void enter(Player player) {
        player.setJumping(true);
        timer = new Timer();
        timer.start();
        jumpingLeftAnimation.start(Time.getCurrentTime());
        player.getPhysicModel().setTargetSpeed(new Vector2f(player.getPhysicModel().getTargetSpeed().x,-Physics.VERTICAL_FORCE));
    }

    @Override
    public PlayerState handleInput(Player player) {
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            buttonLeftPressed = true;
        }else if(Input.isKeyUp(Util.BUTTON_LEFT)){
            buttonLeftPressed = false;
        }
        if(Input.isKeyDown(Util.BUTTON_RIGHT)) {
            buttonRightPressed = true;
        } else if(Input.isKeyUp(Util.BUTTON_RIGHT)) {
            buttonRightPressed = false;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP) && !player.isJumping()){
            exit();
            return idleLeftState;
        }
        if(Input.isKeyUp(Util.BUTTON_UP) && !player.isJumping()){
            exit();
            return idleLeftState;
        }
        if(!player.isJumping() && !player.isJumping()){
            exit();
            return idleLeftState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        /*float horizontalVelocity    = 0;
        float verticalVelocity      = 0;
        if(buttonLeftPressed && player.getPosition().y > 100)
            horizontalVelocity = -player.SPEED * Time.getFrameTimeSeconds();
        if(player.getPosition().y > 0 && Time.getCurrentTime()-jumpStartTime < 300)
            verticalVelocity = -player.JUMP_SPEED * Time.getFrameTimeSeconds() * 2;
        player.updatePosition(new Vector2f(horizontalVelocity, verticalVelocity));*/
        /*if(timer.getElapsedTime() < 500) {
            player.getPhysicModel().setTargetSpeed(new Vector2f(player.getPhysicModel().getTargetSpeed().x,-Physics.VERTICAL_FORCE));
        }*/
        if(buttonLeftPressed)
            player.getPhysicModel().setTargetSpeed(new Vector2f(-Physics.HORIZONTAL_FORCE, player.getPhysicModel().getTargetSpeed().y));
        if(buttonRightPressed)
            player.getPhysicModel().setTargetSpeed(new Vector2f(Physics.HORIZONTAL_FORCE, player.getPhysicModel().getTargetSpeed().y));
        if(!buttonLeftPressed && !buttonRightPressed)
            player.getPhysicModel().setTargetSpeed(new Vector2f(0, player.getPhysicModel().getTargetSpeed().y));
        jumpingLeftAnimation.start(Time.getCurrentTime());
    }

    @Override
    public void exit() {
        timer.stop();
        jumpingLeftAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return jumpingLeftAnimation.getCurrentID();
    }
}
