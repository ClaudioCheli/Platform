package com.example.claudio.platform.toolBox;

import java.nio.FloatBuffer;

/**
 * Created by Claudio on 28/05/2016.
 */
public class Util {

    public static final int LEFT  = 0;
    public static final int RIGHT = 1;

    public static FloatBuffer createFloatBuffer(int dimension){
        FloatBuffer buffer = FloatBuffer.allocate(dimension);
        return buffer;
    }
}