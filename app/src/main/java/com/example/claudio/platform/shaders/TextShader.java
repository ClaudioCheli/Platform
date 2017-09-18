package com.example.claudio.platform.shaders;

import com.example.claudio.platform.R;

/**
 * Created by Claudio on 16/09/2017.
 */

public class TextShader extends Shader {

    private static final int VERTEX_SHADER      = R.raw.text_vertex_shader;
    private static final int FRAGMENT_SHADER    = R.raw.text_fragment_shader;

    private int location_mvpMatrix;

    public TextShader() {
        super(VERTEX_SHADER, FRAGMENT_SHADER);
    }

    @Override
    protected void getAllUniformLocations() {
        location_mvpMatrix = super.getUniformLocation("mvpMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "in_mvpMatrixIndex");
        super.bindAttribute(1, "in_position");
        super.bindAttribute(2, "in_texCoordinate");
    }

    public void loadMVPMatrix(float[] matrix) {
        super.loadMatrix(location_mvpMatrix, matrix);
    }
}
