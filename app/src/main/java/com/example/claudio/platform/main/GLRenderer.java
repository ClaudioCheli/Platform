package com.example.claudio.platform.main;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.example.claudio.platform.builder.EntityCreationDirector;
import com.example.claudio.platform.builder.PlayerBuilder;
import com.example.claudio.platform.builder.TileMapBuilder;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.manager.DisplayManager;
import com.example.claudio.platform.physicsEngine.Physics;
import com.example.claudio.platform.renderEngine.Renderable;
import com.example.claudio.platform.renderEngine.Renderer;
import com.example.claudio.platform.terrains.TileMap;
import com.example.claudio.platform.toolBox.Util;


import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Claudio on 28/05/2016.
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private Renderer renderer;

    private Physics physics;

    private int width;
    private int height;

    private List<Renderable> renderables = new ArrayList<>();
    private List<Entity> physical = new ArrayList<>();
    private TileMap tileMap;

    public GLRenderer(int width, int height){
        this.width  = width;
        this.height = height;
        physics = new Physics();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.i("point", "onSurfaceCreated");
        TileMapBuilder tileMapBuilder = new TileMapBuilder();
        tileMapBuilder.createEntity();
        Log.i("point", "entityCreated");
        tileMapBuilder.createTileset();
        Log.i("point", "tilesetCreated");
        tileMapBuilder.createTileLevels();
        Log.i("point", "tileLevelsCreated");
        tileMapBuilder.createShader();
        Log.i("point", "shaderCreated");
        tileMapBuilder.bindBuffers();
        Log.i("point", "bufferBinded");
        renderables.add(tileMapBuilder.getEntity());
        tileMap = (TileMap) tileMapBuilder.getEntity();
        Util.checkError();

        EntityCreationDirector director = new EntityCreationDirector();
        director.setEntityBuilder(new PlayerBuilder());
        director.createEntity();
        renderables.add(director.getEntity());
        physical.add(director.getEntity());
        Util.checkError();

        GLES20.glClearColor(1.0f, 0.5f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //GLES20.glEnable(GLES20.GL_CULL_FACE);
        //GLES20.glCullFace(GLES20.GL_BACK);

        renderer = new Renderer(width, height);

        DisplayManager.start();
        Log.i("point", "GLRenderer: surfaceCreated");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        this.height = height;
        this.width  = width;
        renderer.setProjectionMatrix(width, height);
        Log.i("point", "GLRenderer: surfaceChanged");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        DisplayManager.update();
        physics.update(physical, tileMap);
        renderer.render(renderables);
    }
}
