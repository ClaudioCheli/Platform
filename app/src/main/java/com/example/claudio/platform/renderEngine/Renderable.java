package com.example.claudio.platform.renderEngine;

/**
 * Created by Claudio on 23/03/2017.
 */

public interface Renderable {

    void render();
    void handleInput();
    void update();
    void bindProjectionMatrix(float[] projectionMatrix);
    void bindViewMatrix(float[] viewMatrix);

}
