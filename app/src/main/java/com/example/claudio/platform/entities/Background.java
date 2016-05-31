package com.example.claudio.platform.entities;

import com.example.claudio.platform.R;
import com.example.claudio.platform.models.RawModel;
import com.example.claudio.platform.models.TexturedModel;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.ModelTexture;
import com.example.claudio.platform.toolBox.SquareBoundingBox;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

/**
 * Created by Claudio on 31/05/2016.
 */
public class Background extends Entity {

    private static final int BACKGROUND_TEXTURE_ID = R.raw.background;

    private float[] backVertices = {
            -4f,  1f, 0,
            -4f, -1f, 0,
            0f, -1f, 0,
            0f,  1f, 0

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

    private int side;

    private SquareBoundingBox leftMargin;
    private SquareBoundingBox rightMargin;

    public Background(Loader loader, Vector3f position, int side){
        super();
        this.side = side;
        RawModel model = loader.loadToVAO(backVertices, backTexCoords, backIndices);
        ModelTexture texture= new ModelTexture(loader.loadTexture(BACKGROUND_TEXTURE_ID));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        texturedModel.getTexture().setHasTransparency(false);
        setEntity(texturedModel, position, dimension,
                new Vector3f(0,0,0) ,new Vector3f(1,1,1));
        leftMargin = new SquareBoundingBox(loader, new Vector2f(0.02f, dimension.y),
                new Vector3f((float)(position.x+0.01), position.y, position.z), true, true);
        rightMargin = new SquareBoundingBox(loader, new Vector2f(0.02f, dimension.y),
                new Vector3f((float)(position.x-dimension.x), position.y, position.z), true, true);
    }


    public int getSide() {
        return side;
    }


    public void setSide(int side) {
        this.side = side;
    }

    public void changeSide(){
        if(side == Util.LEFT)
            side = Util.RIGHT;
        else
            side = Util.LEFT;
    }

    public SquareBoundingBox getLeftMargin(){
        return leftMargin;
    }

    public SquareBoundingBox getRightMargin(){
        return rightMargin;
    }

}
