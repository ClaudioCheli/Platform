package com.example.claudio.platform.camera;

import android.opengl.Matrix;

import com.example.claudio.platform.toolBox.Vector3f;

/**
 * Created by Claudio on 15/05/2017.
 */

public class Camera {

    private Vector3f position   = new Vector3f();
    private Vector3f look       = new Vector3f();
    private Vector3f up         = new Vector3f();

    private float[] view = new float[16];

    public Camera(Vector3f position, Vector3f look, Vector3f up){
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;
        this.look.x     = look.x;
        this.look.y     = look.y;
        this.look.z     = look.z;
        this.up.x       = up.x;
        this.up.y       = up.y;
        this.up.z       = up.z;

        Matrix.setIdentityM(view, 0);

        Matrix.setLookAtM(view, 0, position.x, position.y, position.z,
                look.x, look.y, look.z, up.x, up.y, up.z);
    }

    public void move(Vector3f position){
        this.position.x = position.x;
        this.position.y = position.y;
        this.position.z = position.z;
        this.look.x = position.x;
        this.look.y = position.y;

        Matrix.setLookAtM(view, 0, position.x, position.y, position.z,
                look.x, look.y, look.z, up.x, up.y, up.z);
    }

    public float[] getViewMatrix(){
        return view;
    }

}
