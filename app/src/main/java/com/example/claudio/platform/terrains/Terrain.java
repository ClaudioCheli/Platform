package com.example.claudio.platform.terrains;

import com.example.claudio.platform.R;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.models.RawModel;
import com.example.claudio.platform.models.TexturedModel;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.ModelTexture;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Terrain extends Entity{

    private static final int BACKGROUND_TEXTURE_ID = R.raw.terrain;

    private float[] backVertices = {
            -1f,  0.01f, 0,
            -1f, -1f, 0,
            1f, -1f, 0,
            1f,  0.01f, 0

    };

    private float[] backTexCoords = {
            0,0,
            0,1,
            1,1,
            1,0
    };

    private int[] backIndices = {
            0,1,3,
            3,1,2
    };

    public Terrain(Loader loader){
        super();
        RawModel model = loader.loadToVAO(backVertices, backTexCoords, backIndices);
        ModelTexture texture = new ModelTexture(loader.loadTexture(BACKGROUND_TEXTURE_ID));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        texturedModel.getTexture().setHasTransparency(false);
        setEntity(texturedModel, new Vector3f(0, 0, -8f), dimension,
                new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
    }

}
