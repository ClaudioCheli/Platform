package com.example.claudio.platform.main;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.manager.EntitiesManager;
import com.example.claudio.platform.renderEngine.EntityRenderer;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.MasterRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Claudio on 28/05/2016.
 */
public class GLRenderer implements GLSurfaceView.Renderer {

    private Loader          loader          = new Loader();
    private EntitiesManager entitiesManager = new EntitiesManager();
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
        entitiesManager.instantiateEntities(loader);
        renderer = new MasterRenderer(width, height);
        DisplayManager.start();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        this.height = height;
        this.width  = width;
        renderer.setProjectionMatrix(width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        entitiesManager.updateEntities();
        renderer.loadEntities(entitiesManager);
        renderer.render(entitiesManager.getCamera());
        Log.i("point", "drawFrame");
    }
}
