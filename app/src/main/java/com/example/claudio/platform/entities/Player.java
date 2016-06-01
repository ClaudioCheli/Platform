package com.example.claudio.platform.entities;

import android.util.Log;

import com.example.claudio.platform.R;
import com.example.claudio.platform.models.RawModel;
import com.example.claudio.platform.models.TexturedModel;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.ModelTexture;
import com.example.claudio.platform.toolBox.SquareBoundingBox;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

/**
 * Created by Claudio on 30/05/2016.
 */
public class Player extends Entity{

    private static final int PLAYER_TEXTURE = R.raw.player_texture;

    private float[] vertices = {
            -0.5f, 1f, 0,
            -0.5f, 0,  0,
            0f, 0,  0,
            0f, 1f, 0
    };

    private int[] indices = {
            0,1,3,
            3,1,2
    };

    private float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0
    };

    public Player(Loader loader){
        this.dimension = calculateDimension(vertices);
        this.dimension.x /= 2;
        this.dimension.y /= 2;
        RawModel     model      = loader.loadToVAO(vertices, textureCoords, indices);
        Log.i("point", "Player: modelCreated");
        ModelTexture texture    = new ModelTexture(loader.loadTexture(PLAYER_TEXTURE));
        Log.i("point", "Player: textureCreated");
        texture.setNumberOfRows(8);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        Log.i("point", "Player: texturedModelCreated");
        texturedModel.getTexture().setHasTransparency(true);
        setEntity(texturedModel, 0, new Vector3f(0, 0, -7), dimension,
                new Vector3f(0,0,0) ,new Vector3f(0.5f,0.5f,0.5f));
        Log.i("point", "Player: entitySet");
        this.boundingBox = new SquareBoundingBox(loader, dimension, position, true, true);
        Log.i("point", "Player: boundingBoxCreated");
    }

    protected Vector2f calculateDimension(float[] vertices){
        float minX = findMinX(vertices);
        float maxX = findMaxX(vertices);
        float minY = findMinY(vertices);
        float maxY = findMaxY(vertices);
        //Log.i("point", "minX: " + minX + " maxX: " + maxX + " minY: " + minY + " maxY: " + maxY);
        return new Vector2f(maxX - minX, maxY - minY);
    }

}
