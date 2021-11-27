package com.cc.platform.animations.playerAnimations;

import com.cc.platform.animations.Animation;

/**
 * Created by Claudio on 19/05/2017.
 */

public class RunningLeftAnimation extends Animation {

    public RunningLeftAnimation(int type, String animationName, int animationLength, int frames[]){
        super(type, animationName, animationLength, frames);
        this.frameTime = 200;
    }

    @Override
    public void update(long time) {
        if(time - startTime > frameTime){
            index = (index+1)%length;
            currentID = ids[index];
            startTime += frameTime;
        }
    }

    @Override
    public void start(long time) {
        startTime = time;
        currentID = ids[0];
        index = 0;
    }

    @Override
    public void stop() {
        startTime = 0;
        currentID = ids[0];
        index = 0;
    }

    @Override
    public int getCurrentID() {
        return currentID;
    }

    @Override
    protected void setCurrentID(int id) {

    }
}
