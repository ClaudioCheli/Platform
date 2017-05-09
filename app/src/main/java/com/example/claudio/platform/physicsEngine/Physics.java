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

    private static final float GRAVITY = 100;

    public void update(List<Entity> entities, TileMap tileMap){
        for(Entity entity : entities)
            applyGravity(entity, tileMap);
    }

    private void applyGravity(Entity entity, TileMap tileMap){
        Vector2f entityPosition = entity.getPosition();
        //Log.i("physic","player position " + entityPosition.x + ", " + entityPosition.y);
        int tile, tileDx, tileDiag, tileUnd, tileUndUnd;
        //Log.i("physic","tileMap dimension " + TileMap.dimension.x + ", " + TileMap.dimension.y);
        tile = (int) (TileMap.dimension.x * (int)((entityPosition.y)/64) + entityPosition.x/64);
        tileDx = tile+1;
        tileUnd = (int) (tile+TileMap.dimension.x);
        tileUndUnd = (int) (tileUnd+TileMap.dimension.x);
        //Log.i("physic", "tileNumber " + tile);
        //Log.i("physic", "tileNumberDx " + tileDx);
        //Log.i("physic", "tileNumberDiag " + tileDiag);
        //Log.i("physic", "tileNumberUnd " + tileUnd);
        TileLevel level = tileMap.getTileLevel(0);
        Tileset tileset = tileMap.getTileset(0);

        int tileID = level.getTileId(tileUnd);
        //Log.i("physic", "tileID " + tileID);
        BoundingBox box = tileset.getBoundingBox(tileID);
        Vector2f tilePosition = level.getTilePositions(tileUnd);

        tileID = level.getTileId(tileUndUnd);
        //Log.i("physic", "tileID " + tileID);
        BoundingBox boxUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUnd = level.getTilePositions(tileUndUnd);

        if(box == null){
            if(boxUnd == null){
                //entity.updatePosition(new Vector2f(0.0f,GRAVITY));
                entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
            }else{
                boxUnd.setPosition(tilePositionUnd);
                if(!Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUnd)){
                    //entity.updatePosition(new Vector2f(0.0f,GRAVITY));
                    entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
                }else{
                    return;
                }
            }
        }else{
            box.setPosition(tilePosition);
            if(!Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), box)){
                if(boxUnd == null){
                    //entity.updatePosition(new Vector2f(0.0f,GRAVITY));
                    entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
                }else{
                    boxUnd.setPosition(tilePositionUnd);
                    if(!Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUnd)){
                        //entity.updatePosition(new Vector2f(0.0f,GRAVITY));
                        entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
                    }else{
                        return;
                    }
                }
            }else{
                return;
            }
        }
    }

}