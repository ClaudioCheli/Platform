package com.example.claudio.platform.renderEngine;

import com.example.claudio.platform.entities.Entity;

import java.util.List;

/**
 * Created by Claudio on 30/05/2016.
 */
public interface Manager {

    public void update();

    public void addInEntityList(List<Entity> entities);
}
