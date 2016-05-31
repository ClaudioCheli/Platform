package com.example.claudio.platform.shaders;

import com.example.claudio.platform.R;
import com.example.claudio.platform.entities.Camera;
import com.example.claudio.platform.toolBox.Maths;
import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 28/05/2016.
 */
public class EntityShader extends ShaderProgram{

    private static final int VERTEX_FILE   = R.raw.vertex_shader;
    private static final int FRAGMENT_FILE = R.raw.fragment_shader;

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_numberOfRows;
    private int location_offset;

    public EntityShader(){
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes(){
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations(){
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix  =super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_numberOfRows = super.getUniformLocation("numberOfRows");
        location_offset = super.getUniformLocation("offset");
    }

    public void loadNumberOfRows(int numberOfRows){
        super.loadFloat(location_numberOfRows, numberOfRows);
    }

    public void loadOffset(float x, float y){
        super.load2DVector(location_offset, new Vector2f(x, y));
    }

    public void loadTransformationMatrix(float[] matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(float[] projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadViewMatrix(Camera camera){
        float[] viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

}
