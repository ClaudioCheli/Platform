package com.example.claudio.platform.toolBox;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import java.nio.FloatBuffer;

/**
 * Created by Claudio on 28/05/2016.
 */
public class Util {

    public static final int BUTTON_LEFT     = 0;
    public static final int BUTTON_RIGHT    = 1;
    public static final int BUTTON_UP       = 2;

    public static FloatBuffer createFloatBuffer(int dimension){
        FloatBuffer buffer = FloatBuffer.allocate(dimension);
        return buffer;
    }

    public static void checkError(){
        int error = GLES20.glGetError();
        if(error != 0) {
            Log.i("error", Thread.currentThread().getStackTrace()[3] + " error: " + GLUtils.getEGLErrorString(error));
        }
    }
}
