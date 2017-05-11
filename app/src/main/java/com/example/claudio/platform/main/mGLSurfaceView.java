package com.example.claudio.platform.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import com.example.claudio.platform.entities.Button;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.physicsEngine.Collision;
import com.example.claudio.platform.toolBox.Input;
import com.example.claudio.platform.toolBox.Vector2f;

import java.util.List;

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
    public boolean onTouchEvent(MotionEvent event){
        Input.checkInput(event, mRenderer.getButtons());

        return true;
    }

}
