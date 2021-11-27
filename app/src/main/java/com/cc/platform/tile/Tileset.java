package com.cc.platform.tile;

import com.cc.platform.textures.Texture;
import com.cc.platform.toolBox.BoundingBox;
import com.cc.platform.toolBox.Vector2f;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Claudio on 23/03/2017.
 */

public class Tileset {

    private int textureWidth, textureHeight, tilesNumber, tileWidth,
            tileHeight, firstTileID, lastTileID, columns, rows;
    private String tilesetName;
    private Texture texture;
    private Map<Integer, BoundingBox> boundingBoxes;

    public Tileset(){}

    public Vector2f getTileDimension(){return new Vector2f(tileWidth, tileHeight);}
    public String getName(){return tilesetName;}
    public void setName(String name){this.tilesetName = name;}
    public int getNumberOfRows(){return rows;}
    public void setNumberOfRows(int rows){this.rows = rows;}
    public int getNumberOfColumns(){return columns;}
    public void setNumberOfColumns(int columns){this.columns = columns;}
    public Texture getTexture(){return texture;}
    public void setTexture(Texture texture){this.texture = texture;}
    public int getTextureID(){return texture.getTextureID();}
    public void setBoundingBoxes(Map<Integer, BoundingBox> boxes){this.boundingBoxes = new HashMap<>(boxes);}
    public BoundingBox getBoundingBox(int tileNumber){
        if(boundingBoxes.containsKey(tileNumber)) {
            return boundingBoxes.get(tileNumber);
        } else {
            return null;
        }
    }

}
