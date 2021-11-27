package com.cc.platform.shaders;

import com.cc.platform.R;
import com.cc.platform.toolBox.Vector4f;

/**
 * Created by Claudio on 16/09/2017.
 */

public class TextShader extends Shader {

    private static final int VERTEX_SHADER      = R.raw.text_vertex_shader;
    private static final int FRAGMENT_SHADER    = R.raw.text_fragment_shader;

    private int location_modelMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int location_color;

    public TextShader() {
        super(VERTEX_SHADER, FRAGMENT_SHADER);
    }

    @Override
    protected void getAllUniformLocations() {
        location_modelMatrix        = super.getUniformLocation("model");
        location_viewMatrix         = super.getUniformLocation("view");
        location_projectionMatrix   = super.getUniformLocation("projection");
        location_color              = super.getUniformLocation("u_color");
    }

    @Override
    protected void bindAttributes() {
        //super.bindAttribute(0, "in_mvpMatrixIndex");
        super.bindAttribute(0, "in_position");
        super.bindAttribute(1, "in_texCoordinate");
    }

    public void loadProjectionMatrix(float[] matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(float[] matrix) {
        super.loadMatrix(location_viewMatrix, matrix);
    }

    public void loadModelMatrix(float[] matrix) {
        super.loadMatrix(location_modelMatrix, matrix);
    }

    public void loadColor(Vector4f color) {
        super.loadVector4f(location_color, color);
    }
}
