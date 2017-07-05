package com.example.claudio.platform.builder;

import android.content.res.XmlResourceParser;

import com.example.claudio.platform.R;
import com.example.claudio.platform.animations.Animation;
import com.example.claudio.platform.animations.playerAnimations.IdleLeftAnimation;
import com.example.claudio.platform.animations.playerAnimations.IdleRightAnimation;
import com.example.claudio.platform.animations.playerAnimations.JumpingLeftAnimation;
import com.example.claudio.platform.animations.playerAnimations.JumpingRightAnimation;
import com.example.claudio.platform.animations.playerAnimations.RunningLeftAnimation;
import com.example.claudio.platform.animations.playerAnimations.RunningRightAnimation;
import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.main.MainActivity;
import com.example.claudio.platform.shaders.PlayerShader;
import com.example.claudio.platform.textures.Texture;
import com.example.claudio.platform.tile.Tile;
import com.example.claudio.platform.tile.Tileset;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio on 23/03/2017.
 */

public class PlayerBuilder extends EntityBuilder {

    private static final int PLAYER_FILE = R.xml.knight;
    private static final int PLAYER_DEF = R.xml.knight_def;

    private XmlResourceParser playerDefParser;
    private XmlResourceParser playerFileParser;

    @Override
    public void createEntity() {
        entity = new Player();
    }

    @Override
    public void createTile() {
        playerDefParser  = MainActivity.context.getResources().getXml(PLAYER_DEF);
        int eventType = -1;
        int tileWidth = 0;
        int tileHeight = 0;
        while(eventType != playerDefParser.END_DOCUMENT){
            if(eventType == playerDefParser.START_TAG){
                String node = playerDefParser.getName();
                if(node.equals("tileset")){
                    tileWidth   = Integer.parseInt(playerDefParser.getAttributeValue(null, "tileWidth"));
                    tileHeight  = Integer.parseInt(playerDefParser.getAttributeValue(null, "tileHeight"));
                }
            }
            try {
                eventType = playerDefParser.next();
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
        }

        Tile tile = new Tile(new Vector2f(tileWidth, tileHeight));
        tile.setPosition(new Vector3f(1790,0,0));
        tile.setRotation(new Vector3f(0,0,1), 0);
        tile.setScale(new Vector3f(1f,1f,1f));
        entity.setTile(tile);
    }

    @Override
    public void createTileset() {
        playerDefParser  = MainActivity.context.getResources().getXml(PLAYER_DEF);
        int eventType = -1;
        int textureWidth = 0, textureHeight = 0, tilesNumber = 0, columns = 0, rows = 0, tileWidth = 0, tileHeight = 0;
        String tilesetName = "";
        while(eventType != playerDefParser.END_DOCUMENT){
            if(eventType == playerDefParser.START_TAG){
                String node = playerDefParser.getName();
                if(node.equals("tileset")){
                    textureWidth    = Integer.parseInt(playerDefParser.getAttributeValue(null, "width"));
                    textureHeight   = Integer.parseInt(playerDefParser.getAttributeValue(null, "height"));
                    tilesNumber     = Integer.parseInt(playerDefParser.getAttributeValue(null, "tilesNumber"));
                    tileWidth       = Integer.parseInt(playerDefParser.getAttributeValue(null, "tileWidth"));
                    tileHeight      = Integer.parseInt(playerDefParser.getAttributeValue(null, "tileHeight"));
                    tilesetName     = playerDefParser.getAttributeValue(null, "name");
                    columns         = textureWidth/tileWidth;
                    rows            = textureHeight/tileHeight;
                }
            }
            try {
                eventType = playerDefParser.next();
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
        }

        Tileset tileset = new Tileset();
        tileset.setName(tilesetName);
        tileset.setNumberOfRows(rows);
        tileset.setNumberOfColumns(columns);
        tileset.setTexture(new Texture(R.drawable.knight_img));
        List<Tileset> tilesets = new ArrayList<>();
        tilesets.add(tileset);
        entity.setTileset(tilesets);
    }

    @Override
    public void createAnimation() {
        playerFileParser = MainActivity.context.getResources().getXml(PLAYER_FILE);
        int eventType = -1;
        int animationID, animationLength=0, frameID;
        int frames[] = new int[1];
        List<Animation> animations = new ArrayList<>();
        Animation idleRight = null;
        Animation idleLeft  = null;
        Animation runRight  = null;
        Animation runLeft   = null;
        Animation jumpLeft  = null;
        Animation jumpRight = null;
        String animationName = "";
        while(eventType != playerFileParser.END_DOCUMENT){
            if(eventType == playerFileParser.START_TAG){
                String node = playerFileParser.getName();
                if(node.equalsIgnoreCase("animation")){
                    animationID     = Integer.parseInt(playerFileParser.getAttributeValue(null,"id"));
                    animationLength = Integer.parseInt(playerFileParser.getAttributeValue(null,"length"));
                    animationName   = playerFileParser.getAttributeValue(null,"name");
                    frames = new int[animationLength];
                }
                if(node.equalsIgnoreCase("frame")){
                    frameID         = Integer.parseInt(playerFileParser.getAttributeValue(null,"id"));
                    frames[frameID] = Integer.parseInt(playerFileParser.getAttributeValue(null,"subTextureId"));
                }
            }
            if(eventType == playerFileParser.END_TAG){
                String node = playerFileParser.getName();
                if(node.equalsIgnoreCase("animation")){
                    switch (animationName){
                        case "idle_left":
                            idleLeft = new IdleLeftAnimation(Util.ANIMATION_IDLE_LEFT, animationName, animationLength, frames);
                            animations.add(idleLeft);
                            break;
                        case "idle_right":
                            idleRight = new IdleRightAnimation(Util.ANIMATION_IDLE_RIGHT, animationName, animationLength, frames);
                            animations.add(idleRight);
                            break;
                        case "walk_left":
                            runLeft = new RunningLeftAnimation(Util.ANIMATION_RUN_LEFT, animationName, animationLength, frames);
                            animations.add(runLeft);
                            break;
                        case "walk_right":
                            runRight = new RunningRightAnimation(Util.ANIMATION_RUN_RIGHT, animationName, animationLength, frames);
                            animations.add(runRight);
                            break;
                        case "jump_left":
                            jumpLeft = new JumpingLeftAnimation(Util.ANIMATION_JUMP_LEFT, animationName, animationLength, frames);
                            animations.add(jumpLeft);
                            break;
                        case "jump_right":
                            jumpRight = new JumpingRightAnimation(Util.ANIMATION_JUMP_RIGHT, animationName, animationLength, frames);
                            animations.add(jumpRight);
                            break;
                    }
                }
            }
            try {
                eventType = playerFileParser.next();
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }

        }
        entity.setAnimation(animations);
    }

    @Override
    public void createShader() {
        PlayerShader shader = new PlayerShader();
        entity.setShader(shader);
    }

    @Override
    public void bindBuffers() {
        entity.bindBuffers();
    }
}
