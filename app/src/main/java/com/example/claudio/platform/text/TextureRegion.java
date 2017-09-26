package com.example.claudio.platform.text;

/**
 * Created by Claudio on 21/09/2017.
 */

public class TextureRegion {

    public float u1, v1;                               // Top/Left U,V Coordinates
    public float u2, v2;                               // Bottom/Right U,V Coordinates

    public TextureRegion(float texWidth, float texHeight, float x, float y, float width, float height) {
        this.u1 = x / texWidth;
        this.v1 = y / texHeight;
        this.u2 = this.u1 + (width / texWidth);
        this.v2 = this.v1 + (height / texHeight);
    }

}
