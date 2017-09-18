package com.example.claudio.platform.shaders;

import com.example.claudio.platform.R;
import com.example.claudio.platform.toolBox.Util;

/**
 * Created by Claudio on 24/03/2017.
 */

public class TileMapShader extends Shader {

    private static final int VERTEX_SHADER = R.raw.tile_map_vertex_shader;
    private static final int FRAGMENT_SHADER = R.raw.tile_map_fragment_shader;

    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int location_tilesetNumberOfRows;
    private int location_tilesetNumberOfColumns;

    public TileMapShader(){
        super(VERTEX_SHADER, FRAGMENT_SHADER);
    }

    @Override
    protected void getAllUniformLocations() {
        location_viewMatrix 			= super.getUniformLocation("view");
        location_projectionMatrix 		= super.getUniformLocation("projection");
        location_tilesetNumberOfRows	= super.getUniformLocation("tilesetNumberOfRows");
        location_tilesetNumberOfColumns = super.getUniformLocation("tilesetNumberOfColumns");
        Util.checkError();
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "vertexPosition");
        super.bindAttribute(1, "texCoord");
        super.bindAttribute(2, "tilePosition");
    }

    public void loadTilesetNumberOfRows(int tilesetNumberOfRows){
        super.loadInt(location_tilesetNumberOfRows, tilesetNumberOfRows);
    }

    /**
     * Load in the program Shader.ProgramID the number of tileset's columns
     * @param tilesetNumberOfColumns The number of tileset's columns, in ints
     */
    public void loadTilesetNumberOfColumns(int tilesetNumberOfColumns){
        super.loadInt(location_tilesetNumberOfColumns, tilesetNumberOfColumns);
    }

    /**
     * Load in the program Shader.ProgramID the projection matrix
     * @param matrix The projection Matrix, in org.lwjgl.util.vector.Matrix4f
     */
    public void loadProjectionMatrix(float[] matrix){
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    /**
     * Load in the program Shader.ProgramID the view matrix
     * @param matrix The view Matrix, in org.lwjgl.util.vector.Matrix4f
     */
    public void loadViewMatrix(float[] matrix){
        super.loadMatrix(location_viewMatrix, matrix);
    }

}
