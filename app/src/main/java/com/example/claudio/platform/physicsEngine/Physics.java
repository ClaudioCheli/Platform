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

    private static final float GRAVITY = 200;

    public void update(List<Entity> entities, TileMap tileMap){
        for(Entity entity : entities)
            applyGravity(entity, tileMap);
    }

    private void applyGravity(Entity entity, TileMap tileMap){
        Vector2f entityPosition = entity.getPosition();
        boolean bBox[] = new boolean[4];
        int tile, tileDx, tileDiag, tileUnd, tileUndDx, tileUndUnd, tileUndUndDx;
        tile = (int) (TileMap.dimension.x * (int)((entityPosition.y)/64) + entityPosition.x/64);
        tileDx = tile+1;
        tileUnd = (int) (tile+TileMap.dimension.x);
        tileUndDx = tileUnd+1;
        tileUndUnd = (int) (tileUnd+TileMap.dimension.x);
        tileUndUndDx = tileUndUnd+1;

        TileLevel level = tileMap.getTileLevel(0);
        Tileset tileset = tileMap.getTileset(0);

        int tileID = level.getTileId(tileUnd);
        BoundingBox box = tileset.getBoundingBox(tileID);
        Vector2f tilePosition = level.getTilePositions(tileUnd);

        tileID = level.getTileId(tileUndUnd);
        BoundingBox boxUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUnd = level.getTilePositions(tileUndUnd);

        tileID = level.getTileId(tileUndDx);
        BoundingBox boxUndDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndDx = level.getTilePositions(tileUndDx);

        tileID = level.getTileId(tileUndUndDx);
        BoundingBox boxUndUndDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndUndDx = level.getTilePositions(tileUndUndDx);

        if(box != null){
            box.setPosition(tilePosition);
            bBox[0] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), box);
        }
        if(boxUnd != null){
            boxUnd.setPosition(tilePositionUnd);
            bBox[1] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128),boxUnd);
        }
        if(boxUndDx != null){
            boxUndDx.setPosition(tilePositionUndDx);
            bBox[2] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUndDx);
        }
        if(boxUndUndDx != null){
            boxUndUndDx.setPosition(tilePositionUndUndDx);
            bBox[3] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUndUndDx);
        }

        boolean found = false;
        int i = 0;
        while(found == false && i<bBox.length){
            found = bBox[i];
            i++;
        }

        if(found == false){
            entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
        }
    }

}