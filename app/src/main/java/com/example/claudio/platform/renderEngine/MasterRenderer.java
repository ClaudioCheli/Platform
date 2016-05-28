package com.example.claudio.platform.renderEngine;

import android.opengl.GLES20;
import android.opengl.Matrix;

/**
 * Created by Claudio on 28/05/2016.
 */
public class MasterRenderer {

    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 10f;

    private float[] projectionMatrix;

    public MasterRenderer(int width, int height){
        enableCulling();
        enableBlending();
        createProjectionMatrix(width, height);
    }

    public void createProjectionMatrix(int width, int height){
        projectionMatrix = new float[16];
        float ratio = width/height;
        Matrix.orthoM(projectionMatrix, 0, -ratio, ratio, -1, 1, NEAR_PLANE, FAR_PLANE);
    }

    public static void enableBlending(){
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void disableBlending(){
        GLES20.glDisable(GLES20.GL_BLEND);
    }

    public static void enableCulling(){
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_BACK);
    }

    public static void disableCulling(){
        GLES20.glDisable(GLES20.GL_CULL_FACE);
    }

}
