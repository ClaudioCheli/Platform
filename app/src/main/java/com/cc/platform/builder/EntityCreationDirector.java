package com.cc.platform.builder;


import com.cc.platform.entities.Entity;

/**
 * Created by Claudio on 23/03/2017.
 */

public class EntityCreationDirector {

    private EntityBuilder builder;

    public void setEntityBuilder(EntityBuilder builder) {
        this.builder = builder;
    }

    public void createEntity() {
        builder.createEntity()
                .createTile()
                .createTileset()
                .createAnimation()
                .createShader()
                .bindBuffers();
    }

    public Entity getEntity() {
        return builder.getEntity();
    }

}
