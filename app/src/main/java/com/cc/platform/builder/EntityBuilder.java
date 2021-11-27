package com.cc.platform.builder;

import com.cc.platform.entities.Entity;

/**
 * Created by Claudio on 23/03/2017.
 */

public abstract class EntityBuilder {

    protected Entity entity;

    public Entity getEntity(){return entity;}

    public abstract EntityBuilder createEntity();
    public abstract EntityBuilder createTile();
    public abstract EntityBuilder createTileset();
    public abstract EntityBuilder createAnimation();
    public abstract EntityBuilder createShader();
    public abstract EntityBuilder bindBuffers();

}
