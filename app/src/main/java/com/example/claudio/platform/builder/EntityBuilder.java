package com.example.claudio.platform.builder;

import com.example.claudio.platform.entities.Entity;

/**
 * Created by Claudio on 23/03/2017.
 */

public abstract class EntityBuilder {

    protected Entity entity;

    public Entity getEntity(){return entity;}

    public abstract void createEntity();
    public abstract void createTile();
    public abstract void createTileset();
    public abstract void createAnimation();
    public abstract void createShader();
    public abstract void bindBuffers();

}
