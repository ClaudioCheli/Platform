package com.example.claudio.platform.physicsEngine;

import com.example.claudio.platform.time.Time;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 27/09/2017.
 */

public class PhysicModel {

    //private Vector2f force;
    private Vector2f targetSpeed;
    private Vector2f position;
    private Vector2f currentSpeed;
    private Vector2f acceleration;
    //private float mass;


    public PhysicModel(Vector2f position, Vector2f acceleration) {
        this.position       = new Vector2f(position);
        this.currentSpeed   = new Vector2f();
        this.acceleration   = new Vector2f(acceleration);
        this.targetSpeed    = new Vector2f();
    }

    public void update() {
        float currentSpeedX = acceleration.x * targetSpeed.x + (1-acceleration.x)*currentSpeed.x;
        currentSpeed.x = currentSpeedX;
        float currentSpeedY = acceleration.y * targetSpeed.y + (1-acceleration.y)*currentSpeed.y;
        currentSpeed.y = currentSpeedY;
        /*Vector2f lastAcceleration = new Vector2f(acceleration);
        Vector2f tmp = Vector2f.scalarMultiply(velocity, Time.getFrameTimeSeconds());
        Vector2f tmp2 = Vector2f.scalarMultiply(lastAcceleration, 0.5f*Time.getFrameTimeSeconds()*Time.getFrameTimeSeconds());
        position.add(Vector2f.scalarMultiply(Vector2f.add(tmp, tmp2),100));
        Vector2f newAcceleration = Vector2f.scalarDivide(force, mass);
        tmp = Vector2f.add(lastAcceleration, newAcceleration);
        Vector2f avgAcceleration = Vector2f.scalarMultiply(tmp, 0.5f);
        tmp = Vector2f.scalarMultiply(avgAcceleration, Time.getFrameTimeSeconds());
        velocity= Vector2f.add(velocity, tmp);
        acceleration = new Vector2f(avgAcceleration);*/
    }

    /*public void setForce(Vector2f force) {
        this.force.x = force.x;
        this.force.y = force.y;
    }

    public Vector2f getForce() {
        return force;
    }
*/


    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getCurrentSpeed() {
        return currentSpeed;
    }

    public Vector2f getTargetSpeed() {
        return targetSpeed;
    }

    public void setTargetSpeed(Vector2f speed) {
        this.targetSpeed = new Vector2f(speed);
    }

    public Vector2f getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2f acceleration) {
        acceleration = new Vector2f(acceleration);
    }

   /* public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
    */
}
