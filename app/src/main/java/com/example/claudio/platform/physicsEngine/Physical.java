package com.example.claudio.platform.physicsEngine;

/**
 * Created by Claudio on 29/09/2017.
 */

public interface Physical {

    PhysicModel getPhysicModel();
    void setPhysicModel(PhysicModel model);

    void setJumping(boolean jumping);
    boolean isJumping();

}
