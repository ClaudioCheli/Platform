package com.cc.platform.physicsEngine;

import com.cc.platform.entities.Entity;
import com.cc.platform.entities.Player;
import com.cc.platform.manager.DisplayManager;
import com.cc.platform.terrains.TileMap;
import com.cc.platform.tile.TileLevel;
import com.cc.platform.tile.Tileset;
import com.cc.platform.time.Time;
import com.cc.platform.toolBox.BoundingBox;
import com.cc.platform.toolBox.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio on 07/04/2017.
 */

public class Physics {

    private static final float GRAVITY = 12;
    public static final float HORIZONTAL_FORCE = 10;
    public static final float VERTICAL_FORCE    = 90;

    private Vector2f collisionPoint = new Vector2f();


    public boolean update(List<Physical> physicals, TileMap tileMap) {
        boolean collision = false;
        for(Physical physical : physicals) {

            PhysicModel model = physical.getPhysicModel();

            /*float fy = model.getMass() * 9.8f;
            float fx = model.getForce().x - (0.5f * model.getVelocity().x * model.getVelocity().x);
            model.setForce(new Vector2f( fx, (float) (model.getForce().y + fy - 0.5 * model.getVelocity().y * model.getVelocity().y)));

            if(model.getForce().y > 0) {
                if(checkBottomCollision(model.getPosition(), tileMap)) {
                    model.setForce(new Vector2f(model.getForce().x, 0));
                    model.setVelocity(new Vector2f(model.getVelocity().x, 0));
                    collision = true;
                }
            }*/
            if(model.getTargetSpeed().y < GRAVITY){
                model.setTargetSpeed(new Vector2f(model.getTargetSpeed().x, model.getTargetSpeed().y + GRAVITY));
            } else {
                model.setTargetSpeed(new Vector2f(model.getTargetSpeed().x, GRAVITY));
            }

            model.update();

            Vector2f nextPosition = Vector2f.add(model.getPosition(), model.getCurrentSpeed());

            if(checkBottomCollision(nextPosition, tileMap)){
                model.setPosition(new Vector2f(nextPosition.x, collisionPoint.y));
                collision = true;
            } else {
                model.setPosition(nextPosition);
            }
            if(!collision){
                physical.setJumping(true);
            } else {
                physical.setJumping(false);
            }


            //applyGravity(physical, tileMap);
        }
        return collision;
    }

    private boolean checkBottomCollision(Vector2f entityPosition, TileMap tileMap) {
        int[] tiles = new int[4];
        boolean[] collisions = new boolean[tiles.length];
        int tile = (int) (TileMap.dimension.x * (int) ((entityPosition.y) / 64) + entityPosition.x / 64);
        tiles[0] = (int) (tile + TileMap.dimension.x);          // tileUnd
        tiles[1] = tiles[0] + 1;                                // tileUndDx
        tiles[2] = (int) (tiles[0] + TileMap.dimension.x);      // tileUndUnd
        tiles[3] = tiles[2] + 1;                                // tileUndUndDx

        TileLevel level = tileMap.getTileLevel(0);
        Tileset tileset = tileMap.getTileset(0);


        boolean found = false;
        BoundingBox box = null;
        Vector2f tilePosition = null;
        int i = 0, tileID;
        while(found == false && i < tiles.length) {
            collisions[i] = false;
            tileID = level.getTileId(tiles[i]);
            box = tileset.getBoundingBox(tileID);
            tilePosition = level.getTilePositions(tiles[i]);
            if(box != null) {
                box.setPosition(tilePosition);
                found = Collision.checkBottomCollision(new Vector2f(entityPosition.x + 64, entityPosition.y + 128), box);
                if(found)
                    collisions[i] = true;
            }
            i++;
        }

        for(int j=0; j<collisions.length; j++){
            if(collisions[j]){
                collisionPoint = calculateCollisionPoint(tileset.getBoundingBox(level.getTileId(tiles[j])).getEndpoints(), entityPosition.x + 64);
            }
        }

        return found;
    }

    private void applyGravity(Entity physical, TileMap tileMap) {
        Vector2f entityPosition = physical.getPosition();
        Vector2f displacement = new Vector2f(0.0f, Time.getFrameTimeSeconds() * GRAVITY);
        Vector2f nextPosition = new Vector2f(entityPosition.x + displacement.x, entityPosition.y + displacement.y);
        Vector2f collisionPosition = nextPosition;
        boolean boundigBoxesCollisions[] = new boolean[6];
        for(int i = 0; i < boundigBoxesCollisions.length; i++)
            boundigBoxesCollisions[i] = false;

        //---------
        List<BoundingBox> boxes = new ArrayList<>(12);
        int[] tiles = new int[12];
        tiles[0] = (int) (TileMap.dimension.x * (int) ((entityPosition.y) / 64) + entityPosition.x / 64); //tile
        tiles[1] = tiles[0] + 1;                                                                // tileDx
        tiles[2] = (int) (tiles[0] + TileMap.dimension.x);                                      // tileUnd
        tiles[3] = tiles[2] + 1;                                                                // tileUndDx
        tiles[4] = (int) (tiles[2] + TileMap.dimension.x);                                      // tileUndUnd
        tiles[5] = tiles[4] + 1;                                                                // tileUndUndDx
        tiles[6] = (int) (tiles[0] - TileMap.dimension.x);                                      // tileUp
        tiles[7] = tiles[6] + 1;                                                                // tileUpDx
        tiles[8] = tiles[0] - 1;                                                                // tileSx
        tiles[9] = tiles[2] - 1;                                                                // tileUndSx
        tiles[10] = tiles[1] + 1;                                                               // tileDxDx
        tiles[11] = tiles[3] + 1;                                                               // tileUndDxDx

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
        if(boxUnd != null) {
            boxUnd.setPosition(tilePositionUnd);
            if(boundigBoxesCollisions[1] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUnd))
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
        if(boxUndDx != null) {
            boxUndDx.setPosition(tilePositionUndDx);
            if(boundigBoxesCollisions[4] = Collision.checkBottomCollision(new Vector2f(nextPosition.x + 64, nextPosition.y + 128), boxUndDx))
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

        if(entityPosition.y < 1100)
            physical.updatePosition(collisionPosition.subtract(entityPosition));

        boolean found = false;
        int i = 0;
        while(found == false && i < boundigBoxesCollisions.length) {
            found = boundigBoxesCollisions[i];
            i++;
        }
        /*if(found)
            physical.setJumping(false);
    */}

    private Vector2f calculateCollisionPoint(Vector2f[] endpoints, float entityPosition) {
        Vector2f a = endpoints[0];
        Vector2f b = endpoints[1];
        float yLine = (b.y * (entityPosition - a.x) - a.y * (entityPosition - b.x)) / (b.x - a.x);
        if(a.y == b.y) {
            return new Vector2f(entityPosition - 64, yLine - 128);
        } else {
            return new Vector2f(entityPosition - 64, yLine - 128);
        }
    }

}