package com.example.claudio.platform.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

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

    @Override
    public boolean onTouchEvent(MotionEvent e){
        float x = e.getX();
        float y = e.getY();

        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

}
