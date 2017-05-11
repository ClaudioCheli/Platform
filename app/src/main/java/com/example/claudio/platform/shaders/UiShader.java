package com.example.claudio.platform.shaders;

import com.example.claudio.platform.R;
import com.example.claudio.platform.toolBox.Util;

/**
 * Created by Claudio on 09/05/2017.
 */

public class UiShader extends Shader {

    private static final int VERTEX_SHADER = R.raw.ui_vertex_shader;
    private static final int FRAGMENT_SHADER = R.raw.ui_fragment_shader;

    private int location_modelMatrix;
    private int location_projectionMatrix;
    private int location_textureIndex;
    private int location_tilesetNumberOfRows;
    private int location_tilesetNumberOfColumns;

    public UiShader() {
        super(VERTEX_SHADER, FRAGMENT_SHADER);
        Util.checkError();
    }

    @Override
    protected void getAllUniformLocations() {
        location_modelMatrix 			= super.getUniformLocation("model");
        Util.checkError();
        location_projectionMatrix 		= super.getUniformLocation("projection");
        Util.checkError();
        location_textureIndex			= super.getUniformLocation("textureIndex");
        Util.checkError();
        location_tilesetNumberOfRows	= super.getUniformLocation("tilesetNumberOfRows");
        Util.checkError();
        location_tilesetNumberOfColumns = super.getUniformLocation("tilesetNumberOfColumns");
        Util.checkError();
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "vertexPosition");
        super.bindAttribute(1, "texCoord");
        Util.checkError();
    }

    public void loadTextureIndex(int textureIndex){
        super.loadInt(location_textureIndex, textureIndex);
    }

    public void loadTilesetNumberOfRows(int tilesetNumberOfRows){
        super.loadInt(location_tilesetNumberOfRows, tilesetNumberOfRows);
    }

    public void loadTilesetNumberOfColumns(int tilesetNumberOfColumns){
        super.loadInt(location_tilesetNumberOfColumns, tilesetNumberOfColumns);
    }

    public void loadModelMatrix(float[] matrix){
        super.loadMatrix(location_modelMatrix, matrix);
    }

    public void loadProjectionMatrix(float[] matrix){
        super.loadMatrix(location_projectionMatrix, matrix);
    }

}
