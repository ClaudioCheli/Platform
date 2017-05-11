package com.example.claudio.platform.entities;

import com.example.claudio.platform.animations.Animation;
import com.example.claudio.platform.renderEngine.Renderable;
import com.example.claudio.platform.shaders.Shader;
import com.example.claudio.platform.tile.Tile;
import com.example.claudio.platform.tile.Tileset;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public abstract class Entity implements Renderable{

    public abstract void setTile(Tile tile);
    public abstract void setTileset(List<Tileset> tileset);
    public abstract void setAnimation(Animation animation);
    public abstract void setShader(Shader shader);
    public abstract void bindBuffers();

    public abstract void setType(int type);

    public abstract void updatePosition(Vector2f position);

    public abstract Vector2f getPosition();
    public abstract void setPosition(Vector2f position);

}
