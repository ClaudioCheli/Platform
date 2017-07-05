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

    private static final float GRAVITY = 600;

    public void update(List<Entity> entities, TileMap tileMap){
        for(Entity entity : entities)
            applyGravity(entity, tileMap);
    }

    private void applyGravity(Entity entity, TileMap tileMap) {
        Vector2f entityPosition = entity.getPosition();
        Vector2f displacement = new Vector2f(0.0f, DisplayManager.getFrameTimeSeconds() * GRAVITY);
        Vector2f nextPosition = new Vector2f(entityPosition.x + displacement.x, entityPosition.y + displacement.y);
        Vector2f collisionPosition = nextPosition;
        boolean bBox[] = new boolean[6];
        for (int i = 0; i < bBox.length; i++)
            bBox[i] = false;
        int tile, tileUnd, tileUndUnd, tileDx, tileUndDx, tileUndUndDx;
        tile = (int) (TileMap.dimension.x * (int) ((entityPosition.y) / 64) + entityPosition.x / 64);
        tileDx = tile + 1;
        tileUnd = (int) (tile + TileMap.dimension.x);
        tileUndDx = tileUnd + 1;
        tileUndUnd = (int) (tileUnd + TileMap.dimension.x);
        tileUndUndDx = tileUndUnd + 1;

        TileLevel level = tileMap.getTileLevel(0);
        Tileset tileset = tileMap.getTileset(0);

        int tileID = level.getTileId(tile);
        BoundingBox box = tileset.getBoundingBox(tileID);
        Vector2f tilePosition = level.getTilePositions(tile);

        tileID = level.getTileId(tileUnd);
        BoundingBox boxUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUnd = level.getTilePositions(tileUnd);

        tileID = level.getTileId(tileUndUnd);
        BoundingBox boxUndUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndUnd = level.getTilePositions(tileUndUnd);

        tileID = level.getTileId(tileDx);
        BoundingBox boxDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionDx = level.getTilePositions(tileDx);

        tileID = level.getTileId(tileUndDx);
        BoundingBox boxUndDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndDx = level.getTilePositions(tileUndDx);

        tileID = level.getTileId(tileUndUndDx);
        BoundingBox boxUndUndDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndUndDx = level.getTilePositions(tileUndUndDx);

        if (box != null) {
            box.setPosition(tilePosition);
            if (bBox[0] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), box))
                collisionPosition = new Vector2f(entityPosition.x, box.getPosition().y - 128);
        }
        if (boxUnd != null) {
            boxUnd.setPosition(tilePositionUnd);
            if (bBox[1] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUnd))
                collisionPosition = new Vector2f(entityPosition.x, boxUnd.getPosition().y - 128);
        }
        if (boxUndUnd != null) {
            boxUndUnd.setPosition(tilePositionUndUnd);
            if (bBox[2] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndUnd))
                collisionPosition = new Vector2f(entityPosition.x, boxUndUnd.getPosition().y - 128);
        }
        if (boxDx != null) {
            boxDx.setPosition(tilePositionDx);
            if (bBox[3] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxDx))
                collisionPosition = new Vector2f(entityPosition.x, boxDx.getPosition().y - 128);
        }
        if (boxUndDx != null) {
            boxUndDx.setPosition(tilePositionUndDx);
            if (bBox[4] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndDx))
                collisionPosition = new Vector2f(entityPosition.x, boxUndDx.getPosition().y - 128);
        }
        if (boxUndUndDx != null) {
            boxUndUndDx.setPosition(tilePositionUndUndDx);
            if (bBox[5] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndUndDx))
                collisionPosition = new Vector2f(entityPosition.x, boxUndUndDx.getPosition().y - 128);
        }

        if (entityPosition.y < 850)
            entity.updatePosition(collisionPosition.subtract(entityPosition));

        boolean found = false;
        int i = 0;
        while (found == false && i < bBox.length) {
            found = bBox[i];
            i++;
        }
        if (found)
            entity.setJumping(false);
    }
       /* if(box != null){
            box.setPosition(tilePosition);
            if(bBox[0] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), box))
                collisionPosition = new Vector2f(entityPosition.x, box.getPosition().y);
        }
        if(boxUnd != null){
            boxUnd.setPosition(tilePositionUnd);
            if(bBox[1] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUnd))
                collisionPosition = new Vector2f(entityPosition.x, boxUnd.getPosition().y);
        }
        if(boxUndUnd != null){
            boxUndUnd.setPosition(tilePositionUndUnd);
            if(bBox[2] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128),boxUndUnd))
                collisionPosition = new Vector2f(entityPosition.x, boxUndUnd.getPosition().y);
        }
        if(boxDx != null){
            boxDx.setPosition(tilePositionDx);
            if(bBox[3] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxDx))
                collisionPosition = new Vector2f(entityPosition.x, boxDx.getPosition().y);
        }
        if(boxUndDx != null){
            boxUndDx.setPosition(tilePositionUndDx);
            if(bBox[4] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUndDx))
                collisionPosition = new Vector2f(entityPosition.x, boxUndDx.getPosition().y);
        }
        if(boxUndUndDx != null){
            boxUndUndDx.setPosition(tilePositionUndUndDx);
            if(bBox[5] = Collision.checkBottomCollision(new Vector2f(entityPosition.x+64,entityPosition.y+128), boxUndUndDx))
                collisionPosition = new Vector2f(entityPosition.x, boxUndUndDx.getPosition().y);
        }

        boolean found = false;
        int i = 0;
        while(found == false && i<bBox.length){
            found = bBox[i];
            i++;
        }

        if(found == false){
            if(entityPosition.y < 850)
                entity.updatePosition(collisionPosition.subtract(entityPosition));
                //entity.updatePosition(new Vector2f(0.0f,DisplayManager.getFrameTimeSeconds()*GRAVITY));
        }else {
            entity.setJumping(false);
        }
    }*/

}