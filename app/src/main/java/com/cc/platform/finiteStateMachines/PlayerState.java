package com.cc.platform.finiteStateMachines;

import com.cc.platform.animations.Animation;
import com.cc.platform.animations.playerAnimations.IdleLeftAnimation;
import com.cc.platform.animations.playerAnimations.IdleRightAnimation;
import com.cc.platform.animations.playerAnimations.JumpingLeftAnimation;
import com.cc.platform.animations.playerAnimations.JumpingRightAnimation;
import com.cc.platform.animations.playerAnimations.RunningLeftAnimation;
import com.cc.platform.animations.playerAnimations.RunningRightAnimation;
import com.cc.platform.entities.Player;
import com.cc.platform.toolBox.Util;

import java.util.List;

/**
 * Created by Claudio on 18/05/2017.
 */

public abstract class PlayerState {

    public static IdleLeftState     idleLeftState       = new IdleLeftState();
    public static IdleRightState    idleRightState      = new IdleRightState();
    public static RunningLeftState  runningLeftState    = new RunningLeftState();
    public static RunningRightState runningRightState   = new RunningRightState();
    public static JumpingRightState jumpingRightState   = new JumpingRightState();
    public static JumpingLeftState  jumpingLeftState    = new JumpingLeftState();

    protected static IdleRightAnimation     idleRightAnimation;
    protected static IdleLeftAnimation      idleLeftAnimation;
    protected static RunningRightAnimation  runningRightAnimation;
    protected static RunningLeftAnimation   runningLeftAnimation;
    protected static JumpingRightAnimation  jumpingRightAnimation;
    protected static JumpingLeftAnimation   jumpingLeftAnimation;

    public abstract void enter(Player player);
    public abstract PlayerState handleInput(Player player);
    public abstract void update(Player player);
    public abstract void exit();
    public abstract int getAnimationID();

    public static void setAnimations(List<Animation> animations){
        for(Animation animation : animations){
            switch (animation.getType()){
                case Util.ANIMATION_IDLE_RIGHT:
                    idleRightAnimation = (IdleRightAnimation) animation;
                    break;
                case Util.ANIMATION_IDLE_LEFT:
                    idleLeftAnimation = (IdleLeftAnimation) animation;
                    break;
                case Util.ANIMATION_RUN_RIGHT:
                    runningRightAnimation = (RunningRightAnimation) animation;
                    break;
                case Util.ANIMATION_RUN_LEFT:
                    runningLeftAnimation = (RunningLeftAnimation) animation;
                    break;
                case Util.ANIMATION_JUMP_LEFT:
                    jumpingLeftAnimation = (JumpingLeftAnimation) animation;
                    break;
                case Util.ANIMATION_JUMP_RIGHT:
                    jumpingRightAnimation = (JumpingRightAnimation) animation;
            }
        }
    }

}
