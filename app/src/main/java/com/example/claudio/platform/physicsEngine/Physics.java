package com.example.claudio.platform.physicsEngine;

import android.util.Log;

import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.terrains.TileMap;
import com.example.claudio.platform.tile.TileLevel;
import com.example.claudio.platform.tile.Tileset;
import com.example.claudio.platform.toolBox.BoundingBox;
import com.example.claudio.platform.toolBox.Vector2f;

import java.util.List;

/**
 * Created by Claudio on 07/04/2017.
 */

public class Physics {

    private static final float GRAVITY = 256;

    public void update(List<Entity> entities, TileMap tileMap){
        for(Entity entity : entities)
            applyGravity(entity, tileMap);
    }

    private void applyGravity(Entity entity, TileMap tileMap){
        Vector2f entityPosition = entity.getPosition();
        //Log.i("physic","player position " + entityPosition.x + ", " + entityPosition.y);
        int tile, tileDx, tileDiag, tileUnd;
        //Log.i("physic","tileMap dimension " + TileMap.dimension.x + ", " + TileMap.dimension.y);
        tile = (int) (TileMap.dimension.x * (int)(entityPosition.y/64) + entityPosition.x/64)+1;
        tileDx = tile+1;
        tileUnd = (int) (tile+TileMap.dimension.x);
        tileDiag = tileUnd+1;
        //Log.i("physic", "tileNumber " + tile);
        //Log.i("physic", "tileNumberDx " + tileDx);
        //Log.i("physic", "tileNumberDiag " + tileDiag);
        //Log.i("physic", "tileNumberUnd " + tileUnd);
        TileLevel level = tileMap.getTileLevel(0);
        Tileset tileset = tileMap.getTileset(0);
        //Log.i("physic", "checkBoundingBox");
        int tileID = level.getTileId(tileUnd);
        Log.i("physic","tileID: " + tileID);
        BoundingBox box = tileset.getBoundingBox(tile);

        BoundingBox boxUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePosition = tileMap.getTileLevel(0).getTilePositions(tileUnd-1);

        if(boxUnd != null){
            boxUnd.setPosition(tilePosition);
            Log.i("physic", "boundingBox != null");
            Log.i("physic", "player: " + entityPosition.x + ", " + (entityPosition.y+64));
            Log.i("physic","boxUnd position " + boxUnd.getPosition().x + ", " + boxUnd.getPosition().y);
            if(!Collision.checkBottomCollision(new Vector2f(entityPosition.x,entityPosition.y+64), boxUnd)){
                Log.i("physic", "no collision");
                entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
            }else{
                Log.i("physic","player setPosition: " + entityPosition.x + ", " + boxUnd.getPosition().y);
                entity.setPosition(new Vector2f(entityPosition.x, boxUnd.getPosition().y));
                Log.i("physic", "collision " + entityPosition.x + ", " + entityPosition.y);
            }
        }else{
            Log.i("physic", "boxUnd null");
            entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
        }

    }

}
