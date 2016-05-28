package com.example.claudio.platform.main;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Claudio on 28/05/2016.
 */
public class mGLSurfaceView extends GLSurfaceView{

    private final GLRenderer mRenderer;

    public mGLSurfaceView(Context context, int width, int height){
        super(context);
        setEGLContextClientVersion(2);

        // Imposta GLRenderee come renderer
        mRenderer = new GLRenderer(width, height);
        setRenderer(mRenderer);
    }
}
