package com.cc.platform.animations;

public abstract class Animation {

    protected int type;

    protected int[] ids;
    protected int index;
    protected int currentID;
    protected int length;
    protected float startTime;
    protected String name;
    protected float frameTime;

    public Animation(int type, String animationName, int animationLength, int[] frames) {
        this.type = type;
        this.name = animationName;
        this.length = animationLength;
        this.ids = frames;
    }


    public abstract void update(long time);

    public abstract void start(long time);

    public abstract void stop();

    public int getType() {
        return type;
    }

    public abstract int getCurrentID();

    protected abstract void setCurrentID(int id);

}
