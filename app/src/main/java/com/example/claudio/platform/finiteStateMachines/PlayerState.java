package com.example.claudio.platform.finiteStateMachines;

import com.example.claudio.platform.animations.Animation;
import com.example.claudio.platform.animations.playerAnimations.IdleLeftAnimation;
import com.example.claudio.platform.animations.playerAnimations.IdleRightAnimation;
import com.example.claudio.platform.animations.playerAnimations.JumpingLeftAnimation;
import com.example.claudio.platform.animations.playerAnimations.JumpingRightAnimation;
import com.example.claudio.platform.animations.playerAnimations.RunningLeftAnimation;
import com.example.claudio.platform.animations.playerAnimations.RunningRightAnimation;
import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.toolBox.Util;

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

    public abstract void enter();
    public abstract PlayerState handleInput();
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
