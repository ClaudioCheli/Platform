package com.cc.platform.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import com.cc.platform.communication.Server;
import com.cc.platform.entities.Button;
import com.cc.platform.entities.Entity;
import com.cc.platform.physicsEngine.Collision;
import com.cc.platform.toolBox.Input;
import com.cc.platform.toolBox.Vector2f;

import java.util.List;

/**
 * Created by Claudio on 28/05/2016.
 */
public class mGLSurfaceView extends GLSurfaceView{

    private final GLRenderer mRenderer;

    public mGLSurfaceView(Context context, int width, int height){
        super(context);
        setEGLContextClientVersion(3);

        mRenderer = new GLRenderer(context ,width, height);
        setRenderer(mRenderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        Input.checkInput(event, mRenderer.getButtons());

        return true;
    }

}
