package com.example.claudio.platform.main;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.claudio.platform.renderEngine.MasterRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Claudio on 28/05/2016.
 */
public class GLRenderer implements GLSurfaceView.Renderer {

    private MasterRenderer renderer;

    private int width;
    private int height;

    public GLRenderer(int width, int height){
        this.width  = width;
        this.height = height;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.5f, 0.5f, 1.0f);
        renderer = new MasterRenderer(width, height);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        Log.i("point", "drawFrame");
    }
}
