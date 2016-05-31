package com.example.claudio.platform.models;

import com.example.claudio.platform.renderEngine.ModelTexture;

/**
 * Created by Claudio on 30/05/2016.
 */
public class TexturedModel {

    private RawModel rawModel;
    private ModelTexture texture;

    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel = model;
        this.texture = texture;
    }

    public RawModel getRawModel(){return rawModel;}

    public ModelTexture getTexture(){return texture;}
}
