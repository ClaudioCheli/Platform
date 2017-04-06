package com.example.claudio.platform.manager;




/**
 * Created by Claudio on 31/05/2016.
 */
/*public class BackgroundsManager implements Manager {

    private List<Background> backgrounds = new ArrayList<Background>();

    private Background leftBackground;
    private Background rightBackground;

    private PlayerManager player;
    private Camera camera;

    public BackgroundsManager(Loader loader, PlayerManager player, Camera camera){
        leftBackground = new Background(loader, new Vector3f(0, 0, -9f), Util.LEFT);
        rightBackground = new Background(loader, new Vector3f(4f, 0, -9f), Util.RIGHT);
        Log.i("point", "BackgroundsManager: backgroundsInstantiated");
        backgrounds.add(leftBackground);
        backgrounds.add(rightBackground);
        this.player = player;
        this.camera = camera;
    }

    @Override
    public void update() {
        if(player.getState() == PlayerAnimation.RUN){
            switch (player.getDirection()) {
                case PlayerAnimation.DX:
                    if(Collision.testCollision(rightBackground.getRightMargin(), camera.getBoundingBox()))
                        swap(Util.RIGHT);
                    break;
                case PlayerAnimation.SX:
                    if(Collision.testCollision(leftBackground.getLeftMargin(), camera.getBoundingBox()))
                        swap(Util.LEFT);
            }
        }
    }

    private void swap(int side){
        leftBackground = getLeftBackgroundP();
        rightBackground = getRightBackgroundP();

        if(side == Util.RIGHT){
            //quello di sx deve andare a dx
            leftBackground.setPosition(new Vector3f(rightBackground.getPosition().x+rightBackground.getSize(),
                    leftBackground.getPosition().y, leftBackground.getPosition().z));
        }else{
            //quello di dx deve andare a sx
            rightBackground.setPosition(new Vector3f(leftBackground.getPosition().x-leftBackground.getSize(),
                    rightBackground.getPosition().y, rightBackground.getPosition().z));
        }

        swapBackgroundsSide();
        for(Background background:backgrounds)
            background.changeSide();
    }

    private void swapBackgroundsSide(){
        Background tmp;
        tmp = leftBackground;
        leftBackground = rightBackground;
        rightBackground = tmp;
    }

    private Background getRightBackgroundP(){
        if(backgrounds.get(0).getSide() == Util.RIGHT){
            return backgrounds.get(0);
        }
        return backgrounds.get(1);
    }

    private Background getLeftBackgroundP(){
        if(backgrounds.get(0).getSide() == Util.LEFT){
            return backgrounds.get(0);
        }
        return backgrounds.get(1);
    }

    public Background getLeftBackground(){
        return leftBackground;
    }

    public Background getRightBackground(){
        return rightBackground;
    }

    @Override
    public void addInEntityList(List<Entity> entities) {
        entities.addAll(backgrounds);
    }
}*/
