package com.cc.platform.toolBox;

public interface BoundingBox {
    void setPosition(Vector2f pos);
    Vector2f getPosition();
    Vector2f[] getEndpoints();
}
