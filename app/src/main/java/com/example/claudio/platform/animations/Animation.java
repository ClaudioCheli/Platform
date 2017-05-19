package com.example.claudio.platform.animations;

import java.util.HashMap;
import java.util.Map;

public abstract class Animation {

    protected int type;

    protected int ids[];
    protected int index;
    protected int currentID;
    protected int length;
    protected float startTime;
    protected String name;
    protected float frameTime;

    //protected Map<Integer, Animation> adj = new HashMap<>();

    public Animation(int type, String animationName, int animationLength, int frames[]){
        this.type   = type;
        this.name   = animationName;
        this.length = animationLength;
        this.ids    = frames;
    }


    public abstract void update(long time);
    public abstract void start(long time);
    public abstract void stop();

   /* public void addNode(int event, Animation animation){
        if(!adj.containsKey(event)){
            adj.put(event, animation);
        }
    }

    public Animation next(int event){
        if(adj.containsKey(event))
            return adj.get(event);
        return null;
    }*/

    public int getType(){return type;}

    public abstract int getCurrentID();
    protected abstract void setCurrentID(int id);

    //public void setId(int id){this.id = id;}
    //public int getId(){return id;}

}
