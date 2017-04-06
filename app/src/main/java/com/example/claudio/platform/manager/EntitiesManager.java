package com.example.claudio.platform.manager;


/**
 * Created by Claudio on 30/05/2016.
 */
/*public class EntitiesManager {

    private Manager player;
    private Manager backgrounds;
    private Manager buttons;
    private Manager terrains;
    private Camera  camera;

    private List<Entity> entities = new ArrayList<>();

    public void instantiateEntities(Loader loader){

        player      = new PlayerManager(loader);
        Log.i("point", "EntitiesManager: playerInstantiated");
        terrains    = new TerrainManager(loader);
        Log.i("point", "EntitiesManager: terrainsInstantiated");
        buttons     = new ButtonsManager(loader);
        Log.i("point", "EntitiesManager: buttonsInstantiated");

        camera      = new Camera(player, buttons, terrains, loader);
        Log.i("point", "EntitiesManager: cameraInstantiated");
        backgrounds = new BackgroundsManager(loader, (PlayerManager)player, camera);
        Log.i("point", "EntitiesManager: backgroundsInstantiated");
        backgrounds.addInEntityList(entities);
        terrains.addInEntityList(entities);
        player.addInEntityList(entities);
        buttons.addInEntityList(entities);
        Log.i("point", "EntitiesManager: allEntitiesInstantiated");
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

}*/
