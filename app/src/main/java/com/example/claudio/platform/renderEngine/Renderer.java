package com.example.claudio.platform.renderEngine;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import com.example.claudio.platform.camera.Camera;

import java.util.List;

/**
 * Created by Claudio on 24/03/2017.
 */

public class Renderer {

    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 100f;

    private float[] projectionMatrix = new float[16];

    public Renderer(int width, int height){
        setProjectionMatrix(width, height);
    }

    public void render(List<Renderable> renderables, Camera camera){
        clear();
        for(Renderable renderable : renderables){
            renderable.update();
            renderable.bindProjectionMatrix(projectionMatrix);
            renderable.bindViewMatrix(camera.getViewMatrix());
            renderable.render();
        }
        /*for(Renderable renderable : renderables){
            renderable.clear();
        }*/
    }

    public void clear() {
        GLES20.glClearColor(0.2f, 0.3f, 0.5f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    }

    public void setProjectionMatrix(int width, int height){
        Log.i("point", "renderer: projectionMatrixAllocated");
        //float ratio = ((float) width) / ((float) height);
        //Log.i("point", "MasterRenderer: ratioCalculated: " + ratio + ", width: " + width + ", height: " + height);
        //float[] m, int mOffset, float left, float right, float bottom, float top, float near, float far
        //Matrix.orthoM(projectionMatrix, 0, -ratio, ratio, -1, 1, NEAR_PLANE, FAR_PLANE);
        Matrix.orthoM(projectionMatrix, 0, 0, width, height, 0, NEAR_PLANE, FAR_PLANE);
        Log.i("point", "renderer: ortoMatrixCalculated");
    }

}
