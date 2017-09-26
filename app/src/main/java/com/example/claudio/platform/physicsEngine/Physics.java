package com.example.claudio.platform.physicsEngine;

import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.terrains.TileMap;
import com.example.claudio.platform.tile.TileLevel;
import com.example.claudio.platform.tile.Tileset;
import com.example.claudio.platform.toolBox.BoundingBox;
import com.example.claudio.platform.toolBox.Vector2f;

import java.util.ArrayList;
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
        boolean boundigBoxesCollisions[] = new boolean[6];
        for (int i = 0; i < boundigBoxesCollisions.length; i++)
            boundigBoxesCollisions[i] = false;

        //---------
        List<BoundingBox> boxes = new ArrayList<>(12);
        int[] tiles = new int[12];
        tiles[0] = (int) (TileMap.dimension.x * (int) ((entityPosition.y) / 64) + entityPosition.x / 64);
        tiles[1] = tiles[0] + 1;
        tiles[2] = (int) (tiles[0] + TileMap.dimension.x);
        tiles[3] = tiles[2] + 1;
        tiles[4] = (int) (tiles[2] + TileMap.dimension.x);
        tiles[5] = tiles[4] + 1;
        tiles[6] = (int) (tiles[0] - TileMap.dimension.x);
        tiles[7] = tiles[6] + 1;
        tiles[8] = tiles[0] - 1;
        tiles[9] = tiles[2] - 1;
        tiles[10] = tiles[1] + 1;
        tiles[11] = tiles[3] + 1;

        //--------

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
        /*BoundingBox box = tileset.getBoundingBox(tileID);
        Vector2f tilePosition = level.getTilePositions(tile);*/

        tileID = level.getTileId(tileUnd);
        BoundingBox boxUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUnd = level.getTilePositions(tileUnd);

        tileID = level.getTileId(tileUndUnd);
        BoundingBox boxUndUnd = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndUnd = level.getTilePositions(tileUndUnd);

        /*tileID = level.getTileId(tileDx);
        BoundingBox boxDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionDx = level.getTilePositions(tileDx);*/

        tileID = level.getTileId(tileUndDx);
        BoundingBox boxUndDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndDx = level.getTilePositions(tileUndDx);

        tileID = level.getTileId(tileUndUndDx);
        BoundingBox boxUndUndDx = tileset.getBoundingBox(tileID);
        Vector2f tilePositionUndUndDx = level.getTilePositions(tileUndUndDx);

        /*if (box != null) {
            box.setPosition(tilePosition);
            if (boundigBoxesCollisions[0] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), box))
                collisionPosition = calculateCollisionPoint(box.getEndpoints(), entityPosition);
        }*/
        if (boxUnd != null) {
            boxUnd.setPosition(tilePositionUnd);
            if (boundigBoxesCollisions[1] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUnd))
                collisionPosition = calculateCollisionPoint(boxUnd.getEndpoints(), entityPosition.x + 64);
            else {
                if(boxUndUnd != null) {
                    boxUndUnd.setPosition(tilePositionUndUnd);
                    if(boundigBoxesCollisions[2] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndUnd))
                        collisionPosition = calculateCollisionPoint(boxUndUnd.getEndpoints(), entityPosition.x + 64);
                }
            }
        } else {
            if(boxUndUnd != null) {
                boxUndUnd.setPosition(tilePositionUndUnd);
                if(boundigBoxesCollisions[2] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndUnd))
                    collisionPosition = calculateCollisionPoint(boxUndUnd.getEndpoints(), entityPosition.x + 64);
            }
        }
       /* if (boxDx != null) {
            boxDx.setPosition(tilePositionDx);
            if (boundigBoxesCollisions[3] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxDx))
                collisionPosition = calculateCollisionPoint(boxDx.getEndpoints(), entityPosition);
        }*/
        if (boxUndDx != null) {
            boxUndDx.setPosition(tilePositionUndDx);
            if (boundigBoxesCollisions[4] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndDx))
                collisionPosition = calculateCollisionPoint(boxUndDx.getEndpoints(), entityPosition.x + 64);
            else {
                if(boxUndUndDx != null) {
                    boxUndUndDx.setPosition(tilePositionUndUndDx);
                    if(boundigBoxesCollisions[5] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndUndDx))
                        collisionPosition = calculateCollisionPoint(boxUndUndDx.getEndpoints(), entityPosition.x + 64);
                }
            }
        } else {
            if(boxUndUndDx != null) {
                boxUndUndDx.setPosition(tilePositionUndUndDx);
                if(boundigBoxesCollisions[5] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndUndDx))
                    collisionPosition = calculateCollisionPoint(boxUndUndDx.getEndpoints(), entityPosition.x + 64);
            }
        }

        if (entityPosition.y < 850)
            entity.updatePosition(collisionPosition.subtract(entityPosition));

        boolean found = false;
        int i = 0;
        while (found == false && i < boundigBoxesCollisions.length) {
            found = boundigBoxesCollisions[i];
            i++;
        }
        if (found)
            entity.setJumping(false);
    }

    private Vector2f calculateCollisionPoint(Vector2f[] endpoints, float entityPosition){
        Vector2f a = endpoints[0];
        Vector2f b = endpoints[1];
        float yLine = (b.y * (entityPosition - a.x) - a.y * (entityPosition - b.x)) / (b.x - a.x);
        if(a.y == b.y) {
            return new Vector2f(entityPosition-64, yLine - 128);
        } else {
            return new Vector2f(entityPosition-64, yLine - 128);
        }
    }

}