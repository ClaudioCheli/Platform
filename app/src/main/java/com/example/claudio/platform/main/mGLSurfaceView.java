package com.example.claudio.platform.main;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

/**
 * Created by Claudio on 28/05/2016.
 */
public class mGLSurfaceView extends GLSurfaceView{

    private final GLRenderer mRenderer;

    public mGLSurfaceView(Context context, int width, int height){
        super(context);
        setEGLContextClientVersion(3);
        long mainThread = Thread.currentThread().getId();
        Log.i("playerBuilder", " main thread " + mainThread);
        // Set GLRenderer as renderer
        mRenderer = new GLRenderer(width, height);
        setRenderer(mRenderer);
    }
}
