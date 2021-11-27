package com.cc.platform.shaders;

import android.content.Context;

import com.cc.platform.R;
import com.cc.platform.toolBox.Util;

/**
 * Created by Claudio on 24/03/2017.
 */

public class PlayerShader extends Shader {

    private static final int VERTEX_SHADER = R.raw.player_vertex_shader;
    private static final int FRAGMENT_SHADER = R.raw.player_fragment_shader;

    private int location_modelMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int location_textureIndex;
    private int location_tilesetNumberOfRows;
    private int location_tilesetNumberOfColumns;

    public PlayerShader(Context context) {
        super(VERTEX_SHADER, FRAGMENT_SHADER, context);
        Util.checkError();
    }

    @Override
    protected void getAllUniformLocations() {
        location_modelMatrix = getUniformLocation("model");
        location_viewMatrix = getUniformLocation("view");
        location_projectionMatrix = getUniformLocation("projection");
        location_textureIndex = getUniformLocation("textureIndex");
        location_tilesetNumberOfRows = getUniformLocation("tilesetNumberOfRows");
        location_tilesetNumberOfColumns = getUniformLocation("tilesetNumberOfColumns");
        Util.checkError();
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "vertexPosition");
        super.bindAttribute(1, "texCoord");
        Util.checkError();
    }

    public void loadTextureIndex(int textureIndex) {
        super.loadInt(location_textureIndex, textureIndex);
    }

    public void loadTilesetNumberOfRows(int tilesetNumberOfRows) {
        super.loadInt(location_tilesetNumberOfRows, tilesetNumberOfRows);
    }

    public void loadTilesetNumberOfColumns(int tilesetNumberOfColumns) {
        super.loadInt(location_tilesetNumberOfColumns, tilesetNumberOfColumns);
    }

    public void loadModelMatrix(float[] matrix) {
        super.loadMatrix(location_modelMatrix, matrix);
    }

    public void loadProjectionMatrix(float[] matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(float[] matrix) {
        super.loadMatrix(location_viewMatrix, matrix);
    }

}
