package com.example.claudio.platform.renderEngine;

/**
 * Created by Claudio on 30/05/2016.
 */
public class ModelTexture {

    private int textureID;

    private boolean hasTransparency = false;

    private int numberOfRows = 1;

    public ModelTexture(int textureID){this.textureID = textureID;}

    public int getNumberOfRows(){return numberOfRows;}

    public void setNumberOfRows(int numberOfRows){this.numberOfRows = numberOfRows;}

    public int getTextureID(){return textureID;}

    public boolean isHasTransparency(){return hasTransparency;}

    public void setHasTransparency(boolean hasTransparency){this.hasTransparency = hasTransparency;}

}
