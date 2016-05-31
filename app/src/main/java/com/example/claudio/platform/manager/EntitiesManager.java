package com.example.claudio.platform.manager;

import com.example.claudio.platform.entities.Camera;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.Manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public class EntitiesManager {

    private Manager player;
    private Manager backgrounds;
    private Manager buttons;
    private Manager terrains;
    private Camera  camera;

    private List<Entity> entities = new ArrayList<>();

    public void instantiateEntities(Loader loader){
        player      = new PlayerManager(loader);
        terrains    = new TerrainManager(loader);
        buttons     = new ButtonsManager(loader);
        camera      = new Camera(player, buttons, terrains, loader);
        backgrounds = new BackgroundsManager(loader, (PlayerManager)player, camera);

        backgrounds.addInEntityList(entities);
        terrains.addInEntityList(entities);
        player.addInEntityList(entities);
        buttons.addInEntityList(entities);
    }

    public void updateEntities(){
        player.update();
        camera.update();
        backgrounds.update();
        terrains.update();
        buttons.update();
    }

    public Manager getPlayer(){
        return player;
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public Camera getCamera(){
        return camera;
    }

}
