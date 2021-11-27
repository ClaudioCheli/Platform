package com.cc.platform.finiteStateMachines;

import com.cc.platform.entities.Player;
import com.cc.platform.physicsEngine.Physics;
import com.cc.platform.time.Time;
import com.cc.platform.time.Timer;
import com.cc.platform.toolBox.Input;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 18/05/2017.
 */

public class JumpingRightState extends PlayerState {

    private boolean buttonLeftPressed   = false;
    private boolean buttonRightPressed  = false;
    private Timer timer;

    @Override
    public void enter(Player player) {
        player.setJumping(true);
        timer = new Timer();
        timer.start();
        jumpingRightAnimation.start(Time.getCurrentTime());
        player.getPhysicModel().setTargetSpeed(new Vector2f(player.getPhysicModel().getTargetSpeed().x,-Physics.VERTICAL_FORCE));
    }

    @Override
    public PlayerState handleInput(Player player) {
        if(Input.isKeyDown(Util.BUTTON_RIGHT)){
            buttonRightPressed = true;
        }else if(Input.isKeyUp(Util.BUTTON_RIGHT)){
            buttonRightPressed = false;
        }
        if(Input.isKeyDown(Util.BUTTON_LEFT)){
            buttonLeftPressed = true;
        }else if(Input.isKeyUp(Util.BUTTON_LEFT)){
            buttonLeftPressed = false;
        }
        if(Input.isKeyUp(Util.BUTTON_RIGHT) && Input.isKeyUp(Util.BUTTON_LEFT) && Input.isKeyUp(Util.BUTTON_UP) && !player.isJumping()){
            exit();
            return idleRightState;
        }
        if(Input.isKeyUp(Util.BUTTON_UP)  && !player.isJumping()){
            exit();
            return idleRightState;
        }
        if(!player.isJumping() && !player.isJumping()){
            exit();
            return idleRightState;
        }
        return null;
    }

    @Override
    public void update(Player player) {
        /*float horizontalVelocity    = 0;
        float verticalVelocity      = 0;
        if(buttonRightPressed)
            horizontalVelocity = player.SPEED * Time.getFrameTimeSeconds();
        if(player.getPosition().y > 0 && Time.getCurrentTime()-jumpStartTime < 300)
            verticalVelocity =  -player.JUMP_SPEED * Time.getFrameTimeSeconds() * 2;
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
        jumpingRightAnimation.update(Time.getCurrentTime());
    }

    @Override
    public void exit() {
        timer.stop();
        jumpingRightAnimation.stop();
    }

    @Override
    public int getAnimationID() {
        return jumpingRightAnimation.getCurrentID();
    }
}
