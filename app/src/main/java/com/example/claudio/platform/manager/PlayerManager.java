package com.example.claudio.platform.manager;

import android.util.Log;

import com.example.claudio.platform.animations.PlayerAnimation;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.entities.Player;
import com.example.claudio.platform.renderEngine.Loader;
import com.example.claudio.platform.renderEngine.Manager;

import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public class PlayerManager implements Manager{

    private Player player;
    private PlayerAnimation animation;

    private int state       = 0;
    private int direction   = 0;

    public PlayerManager(Loader loader){
        player      = new Player(loader);
        Log.i("point", "PlayerManager: playerCreated");
        animation   = new PlayerAnimation();
        state       = animation.getState();
        direction   = animation.getDirection();
        player.setIndex(0);
    }

    @Override
    public void update() {
        if (animation.move() == PlayerAnimation.RUN){
            switch (animation.getDirection()){
                case PlayerAnimation.DX:
                    player.increasePosition(0.01f, 0f, 0f);
                    break;
                case PlayerAnimation.SX:
                    player.increasePosition(-0.01f, 0f, 0f);
            }
        }
        animation.update();
        state       = animation.getState();
        direction   = animation.getDirection();
        player.setIndex(animation.getIndex());
    }

    @Override
    public void addInEntityList(List<Entity> entities) {
        entities.add(player);
    }

    public int getState(){return state;}

    public int getDirection(){return direction;}
}
