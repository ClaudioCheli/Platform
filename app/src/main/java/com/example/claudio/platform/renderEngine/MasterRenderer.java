package com.example.claudio.platform.renderEngine;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.example.claudio.platform.entities.Camera;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.manager.EntitiesManager;
import com.example.claudio.platform.models.TexturedModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudio on 28/05/2016.
 */
public class MasterRenderer {

    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 10f;

    private EntityRenderer entityRenderer;

    private float[] projectionMatrix;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

    public MasterRenderer(int width, int height){
        enableCulling();
        enableBlending();
        createProjectionMatrix(width, height);
        entityRenderer = new EntityRenderer(projectionMatrix);
    }

    public void render(Camera camera){
        prepare();
        entityRenderer.render(entities, camera);
        clear();
    }

    public void loadEntities(EntitiesManager manager){
        List<Entity> cEntities = manager.getEntities();
        TexturedModel entityModel;
        for(Entity entity: cEntities){
            entityModel = entity.getModel();
            List<Entity> batch = entities.get(entityModel);
            if(batch != null){
                batch.add(entity);
            }
            else {
                List<Entity> newBatch = new ArrayList<Entity>();
                newBatch.add(entity);
                entities.put(entityModel, newBatch);
            }
        }
    }

    public void prepare(){
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        GLES20.glClearColor(0,0,0,1);
    }

    private void clear(){
        entities.clear();
    }

    public void setProjectionMatrix(int width, int height){
        createProjectionMatrix(width, height);
    }

    public void createProjectionMatrix(int width, int height){
        projectionMatrix = new float[16];
        float ratio = width/height;
        Matrix.orthoM(projectionMatrix, 0, -ratio, ratio, -1, 1, NEAR_PLANE, FAR_PLANE);
    }

    public static void enableBlending(){
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void disableBlending(){
        GLES20.glDisable(GLES20.GL_BLEND);
    }

    public static void enableCulling(){
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_BACK);
    }

    public static void disableCulling(){
        GLES20.glDisable(GLES20.GL_CULL_FACE);
    }

}
