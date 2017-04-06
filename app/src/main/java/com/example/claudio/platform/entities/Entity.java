package com.example.claudio.platform.entities;

import com.example.claudio.platform.renderEngine.Renderable;
import com.example.claudio.platform.shaders.Shader;
import com.example.claudio.platform.tile.Tile;
import com.example.claudio.platform.tile.Tileset;

import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public abstract class Entity implements Renderable{

    public abstract void setTile(Tile tile);
    public abstract void setTileset(List<Tileset> tileset);
    public abstract void setAnimation();
    public abstract void setShader(Shader shader);
    public abstract void bindBuffers();

}
