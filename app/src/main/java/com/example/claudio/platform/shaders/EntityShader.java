package com.example.claudio.platform.shaders;

import com.example.claudio.platform.R;
import com.example.claudio.platform.entities.Camera;
import com.example.claudio.platform.toolBox.Maths;

/**
 * Created by Claudio on 28/05/2016.
 */
public class EntityShader extends ShaderProgram{

    private static final int VERTEX_FILE   = R.raw.vertex_shader;
    private static final int FRAGMENT_FILE = R.raw.fragment_shader;

    private int location_projectionMatrix;
    private int location_transformationMatrix;
    private int location_viewMatrix;

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

    public void loadTransformationMatrix(float[] matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera){
        float[] viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadProjectionMatrix(float[] projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

}
