package com.example.claudio.platform.shaders;

import com.example.claudio.platform.R;

/**
 * Created by Claudio on 28/05/2016.
 */
public class EntityShader extends ShaderProgram{

    private static final int VERTEX_FILE   = R.raw.vertex_shader;
    private static final int FRAGMENT_FILE = R.raw.fragment_shader;

    private int location_projectionMatrix;

    public EntityShader(){
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes(){
        super.bindAttributes(0, "position");
    }

    @Override
    protected void getAllUniformLocations(){
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    public void loadProjectionMatrix(float[] projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

}
