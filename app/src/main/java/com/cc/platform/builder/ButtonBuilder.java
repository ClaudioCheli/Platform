package com.cc.platform.builder;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;

import com.cc.platform.R;
import com.cc.platform.animations.Animation;
import com.cc.platform.animations.UiAnimation;
import com.cc.platform.entities.Button;
import com.cc.platform.shaders.UiShader;
import com.cc.platform.textures.Texture;
import com.cc.platform.tile.Tile;
import com.cc.platform.tile.Tileset;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;
import com.cc.platform.toolBox.Vector3f;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio on 09/05/2017.
 */

public class ButtonBuilder extends EntityBuilder {

    private static final int BUTTONS_FILE = R.xml.buttons;
    private static final int BUTTONS_DEF = R.xml.buttons_def;

    private final Context context;

    private final int buttonTipe;

    public ButtonBuilder(int buttonType, Context context) {
        this.buttonTipe = buttonType;
        this.context = context;
    }

    @Override
    public EntityBuilder createEntity() {
        entity = new Button();
        entity.setType(buttonTipe);
        return this;
    }

    @Override
    public EntityBuilder createTile() {
        Vector2f tileDimension = getTileDimension();
        Tile tile = new Tile(tileDimension);
        tile.setPosition(getButtonPosition());
        tile.setRotation(new Vector3f(0, 0, 1), 0);
        tile.setScale(new Vector3f(1, 1, 1));
        entity.setTile(tile);
        return this;
    }

    private Vector2f getTileDimension() {
        try (XmlResourceParser buttonsDefParser = context.getResources().getXml(BUTTONS_DEF)) {
            int eventType;
            while ((eventType = buttonsDefParser.next()) != buttonsDefParser.END_DOCUMENT) {
                if (eventType != buttonsDefParser.START_TAG) {
                    continue;
                }
                if ("tileset".equalsIgnoreCase(buttonsDefParser.getName())) {
                    int tileWidth = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "tileWidth"));
                    int tileHeight = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "tileHeight"));
                    return new Vector2f(tileWidth, tileHeight);
                }
            }
        } catch (Throwable t) {
            Log.e("builder", t.getMessage(), t);
        }
        return new Vector2f();
    }

    private Vector3f getButtonPosition() {
        switch (buttonTipe) {
            case Util.BUTTON_LEFT:
                return new Vector3f(10, 900, 0);
            case Util.BUTTON_RIGHT:
                return new Vector3f(300, 900, 0);
            case Util.BUTTON_UP:
                return new Vector3f(1700, 900, 0);
        }
        return new Vector3f();
    }

    @Override
    public EntityBuilder createTileset() {
        Tileset tileset = createTilesetFromResource();
        tileset.setTexture(new Texture(R.drawable.buttons_img, context));
        List<Tileset> tilesets = new ArrayList<>();
        tilesets.add(tileset);
        entity.setTileset(tilesets);
        return this;
    }

    private Tileset createTilesetFromResource() {
        try (XmlResourceParser buttonsDefParser = context.getResources().getXml(BUTTONS_DEF)) {
            int eventType;
            while ((eventType = buttonsDefParser.next()) != buttonsDefParser.END_DOCUMENT) {
                if (eventType != buttonsDefParser.START_TAG) {
                    continue;
                }
                String node = buttonsDefParser.getName();
                if ("tileset".equalsIgnoreCase(node)) {
                    int textureWidth = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "width"));
                    int textureHeight = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "height"));
                    int tileWidth = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "tileWidth"));
                    int tileHeight = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "tileHeight"));
                    String tilesetName = buttonsDefParser.getAttributeValue(null, "name");
                    int columns = textureWidth / tileWidth;
                    int rows = textureHeight / tileHeight;
                    Tileset tileset = new Tileset();
                    tileset.setName(tilesetName);
                    tileset.setNumberOfRows(rows);
                    tileset.setNumberOfColumns(columns);
                    return tileset;
                }
            }
        } catch (Throwable t) {
            Log.e("builder", t.getMessage(), t);
        }
        return new Tileset();
    }

    @Override
    public EntityBuilder createAnimation() {
        XmlResourceParser buttonsDefParser = context.getResources().getXml(BUTTONS_DEF);
        int eventType = -1;
        int tileId = 0;
        while (eventType != buttonsDefParser.END_DOCUMENT) {
            if (eventType == buttonsDefParser.START_TAG) {
                String node = buttonsDefParser.getName();
                if (node.equalsIgnoreCase("SubTexture")) {
                    String name = buttonsDefParser.getAttributeValue(null, "name");
                    int type = -1;
                    switch (name) {
                        case "button_left.png":
                            type = Util.BUTTON_LEFT;
                            break;
                        case "button_right.png":
                            type = Util.BUTTON_RIGHT;
                            break;
                        case "button_up.png":
                            type = Util.BUTTON_UP;
                            break;
                    }
                    if (type == buttonTipe) {
                        tileId = Integer.parseInt(buttonsDefParser.getAttributeValue(null, "id"));
                    }
                }
            }
            try {
                eventType = buttonsDefParser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int[] ids = new int[1];
        ids[0] = tileId + 1;
        List<Animation> animations = new ArrayList<>();
        animations.add(new UiAnimation(1, ids));
        entity.setAnimation(animations);
        return this;
    }

    @Override
    public EntityBuilder createShader() {
        UiShader shader = new UiShader(context);
        entity.setShader(shader);
        return this;
    }

    @Override
    public EntityBuilder bindBuffers() {
        entity.bindBuffers();
        return this;
    }
}
