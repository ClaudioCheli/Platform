package com.cc.platform.builder;


import com.cc.platform.entities.Entity;

/**
 * Created by Claudio on 23/03/2017.
 */

public class EntityCreationDirector {

    private EntityBuilder builder;

    public void createEntity(){
        builder.createEntity();
        builder.createTile();
        builder.createTileset();
        builder.createAnimation();
        builder.createShader();
        builder.bindBuffers();
    }

    public void setEntityBuilder(EntityBuilder builder){
        this.builder = builder;
    }

    public Entity getEntity(){return builder.getEntity();}


}
