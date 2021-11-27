package com.cc.platform.entities;

import com.cc.platform.animations.Animation;
import com.cc.platform.renderEngine.Renderable;
import com.cc.platform.shaders.Shader;
import com.cc.platform.tile.Tile;
import com.cc.platform.tile.Tileset;
import com.cc.platform.toolBox.Vector2f;
import com.cc.platform.toolBox.Vector3f;

import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public abstract class Entity implements Renderable{

    public abstract void setTile(Tile tile);
    public abstract void setTileset(List<Tileset> tileset);
    public abstract void setAnimation(List<Animation> animations);
    public abstract void setShader(Shader shader);
    public abstract void bindBuffers();

    public abstract void setType(int type);

    public abstract void updatePosition(Vector2f position);

    public abstract Vector2f getPosition();
    public abstract void setPosition(Vector2f position);



}
