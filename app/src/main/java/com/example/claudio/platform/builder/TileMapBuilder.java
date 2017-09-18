package com.example.claudio.platform.builder;

import android.content.res.XmlResourceParser;

import com.example.claudio.platform.R;
import com.example.claudio.platform.main.MainActivity;
import com.example.claudio.platform.renderEngine.Renderable;
import com.example.claudio.platform.shaders.TileMapShader;
import com.example.claudio.platform.terrains.TileMap;
import com.example.claudio.platform.textures.Texture;
import com.example.claudio.platform.tile.TileLevel;
import com.example.claudio.platform.tile.Tileset;
import com.example.claudio.platform.toolBox.BoundingBox;
import com.example.claudio.platform.toolBox.LineBoundingBox;
import com.example.claudio.platform.toolBox.SquareBoundingBox;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudio on 29/03/2017.
 */

public class TileMapBuilder {

    private TileMap tileMap;

    private static final int TILEMAP_FILE = R.xml.level1;
    private static final int TILEMAP_DEF = R.xml.terrain_tileset;

    private XmlResourceParser tileMapDefParser;
    private XmlResourceParser tileMapFileParser;

    public void createEntity(){
        tileMap = new TileMap();
    }

    public void createTileset(){
        tileMapDefParser  = MainActivity.context.getResources().getXml(TILEMAP_DEF);
        Tileset tileset = null;
        List<Tileset> tilesets = new ArrayList<>();
        Map<Integer, BoundingBox> boundingBoxes = new HashMap<>();
        int eventType, textureWidth, textureHeight, tileWidth, tileHeight, tileID=0;
        String boxType = "";
        Vector2f boundingBoxPosition    = new Vector2f();
        Vector2f boundingBoxDimension   = new Vector2f();
        Vector2f boundingBoxEndpoints[]   = new Vector2f[2];
        try{
            eventType = tileMapDefParser.getEventType();
            while(eventType != tileMapDefParser.END_DOCUMENT) {
                if (eventType == tileMapDefParser.START_TAG) {
                    String node = tileMapDefParser.getName();
                    switch (node){
                        case "tileset":
                            tileset = new Tileset();
                            textureWidth = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "width"));
                            textureHeight = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "height"));
                            tileWidth = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "tilewidth"));
                            tileHeight = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "tileheight"));
                            tileset.setName(tileMapDefParser.getAttributeValue(null, "name"));
                            tileset.setNumberOfRows(textureHeight / tileHeight);
                            tileset.setNumberOfColumns(textureWidth / tileWidth);
                            tileset.setTexture(new Texture(R.drawable.terrain));
                            tilesets.add(tileset);
                            break;
                        case "tile":
                            tileID = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "id"))+1;
                            break;
                        case "object":
                            boxType = tileMapDefParser.getAttributeValue(null, "type");
                            boundingBoxPosition.x = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "x"));
                            boundingBoxPosition.y = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "y"));
                            if(boxType.equalsIgnoreCase("square")) {
                                boundingBoxDimension.x = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "width"));
                                boundingBoxDimension.y = Integer.parseInt(tileMapDefParser.getAttributeValue(null, "height"));
                            }
                            break;
                        case "polygon":
                            String allPoints = tileMapDefParser.getAttributeValue(null, "points");
                            String point[] = allPoints.split(" ");
                            String coordinate[];
                            coordinate = point[0].split(",");
                            boundingBoxEndpoints[0] = new Vector2f();
                            boundingBoxEndpoints[0].x = Integer.parseInt(coordinate[0]);
                            boundingBoxEndpoints[0].y = Integer.parseInt(coordinate[1]);
                            coordinate = point[1].split(",");
                            boundingBoxEndpoints[1] = new Vector2f();
                            boundingBoxEndpoints[1].x = Integer.parseInt(coordinate[0]);
                            boundingBoxEndpoints[1].y = Integer.parseInt(coordinate[1]);
                            break;
                    }
                }else if(eventType == tileMapDefParser.END_TAG){
                    String node = tileMapDefParser.getName();
                    switch (node){
                        case "tile":
                            if(boxType.equalsIgnoreCase("square")) {
                                boundingBoxes.put(tileID, new SquareBoundingBox(new Vector2f(boundingBoxPosition), new Vector2f(boundingBoxDimension)));
                            }
                            else if(boxType.equalsIgnoreCase("line")) {
                                boundingBoxes.put(tileID, new LineBoundingBox(new Vector2f(boundingBoxPosition), boundingBoxEndpoints));
                            }
                            break;
                        case "tileset":
                            tileset.setBoundingBoxes(boundingBoxes);
                            break;
                    }
                }

                eventType = tileMapDefParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tileMap.setTileset(tilesets);
    }

    public void createTileLevels(){
        tileMapFileParser  = MainActivity.context.getResources().getXml(TILEMAP_FILE);
        TileLevel level = null;
        List<TileLevel> tileLevels = new ArrayList<>();
        String data = "";
        int tileWidth = 0, tileHeight = 0;
        int eventType;
        try {
            eventType = tileMapFileParser.getEventType();
            while(eventType != XmlResourceParser.END_DOCUMENT){
                String tagName = tileMapFileParser.getName();
                switch (eventType) {
                    case XmlResourceParser.START_TAG:
                        if(tagName.equalsIgnoreCase("map")){
                            tileWidth = Integer.parseInt(tileMapFileParser.getAttributeValue(null, "tilewidth"));
                            tileHeight = Integer.parseInt(tileMapFileParser.getAttributeValue(null, "tileheight"));
                        }
                        if(tagName.equalsIgnoreCase("layer")){
                            level = new TileLevel();
                            level.setName(tileMapFileParser.getAttributeValue(null, "name"));
                            level.setLevelWidth(Integer.parseInt(tileMapFileParser.getAttributeValue(null, "width")));
                            level.setLevelHeight(Integer.parseInt(tileMapFileParser.getAttributeValue(null, "height")));
                            level.setTileWidth(tileWidth);
                            level.setTileHeight(tileHeight);
                        }
                        break;
                    case XmlResourceParser.TEXT:
                        data = tileMapFileParser.getText();
                        break;
                    case XmlResourceParser.END_TAG:
                        if(tagName.equalsIgnoreCase("data")){
                            level.setData(data);
                        } else if(tagName.equalsIgnoreCase("layer")){
                            level.calculatePositions();
                            tileLevels.add(level);
                        }
                        break;
                }
                eventType = tileMapFileParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tileMap.setTileLevels(tileLevels);
    }

    public void createShader(){
        TileMapShader shader = new TileMapShader();
        tileMap.setShader(shader);
        Util.checkError();
    }

    public void bindBuffers(){
        tileMap.bindBuffers();
        Util.checkError();
    }

    public Renderable getEntity(){return tileMap;}

}
