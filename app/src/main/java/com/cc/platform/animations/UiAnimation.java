package com.cc.platform.animations;

import com.cc.platform.toolBox.Util;

/**
 * Created by Claudio on 09/05/2017.
 */

public class UiAnimation extends Animation{

    public UiAnimation(int animationLength, int frames[]){
        super(Util.ANIMATION_BUTTON, "button", animationLength, frames);
        setCurrentID(ids[0]);
    }

    @Override
    public void update(long time) {

    }

    @Override
    public void start(long time) {

    }

    @Override
    public void stop() {

    }

    @Override
    public int getCurrentID() {
        return currentID;
    }

    @Override
    protected void setCurrentID(int id) {
        this.currentID = id;
    }
}
