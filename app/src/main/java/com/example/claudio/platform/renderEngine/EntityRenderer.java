package com.example.claudio.platform.renderEngine;

/*import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.claudio.platform.entities.Camera;
import com.example.claudio.platform.entities.Entity;
import com.example.claudio.platform.models.RawModel;
import com.example.claudio.platform.models.TexturedModel;
import com.example.claudio.platform.toolBox.Maths;

import java.util.List;
import java.util.Map;
*/
/**
 * Created by Claudio on 28/05/2016.
 */
/*public class EntityRenderer {

    private EntityShader shader;

    public EntityRenderer(float[] projectionMatrix){
        shader = new EntityShader();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<TexturedModel, List<Entity>> entities, Camera camera){
        shader.start();
        shader.loadViewMatrix(camera);
        for(TexturedModel model:entities.keySet()){
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);
            for(Entity entity:batch){
                prepareInstances(entity);
                GLES20.glDrawElements(GLES20.GL_TRIANGLES, model.getRawModel().getVertexCount(),
                        GLES20.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
        shader.stop();
    }

    private void prepareTexturedModel(TexturedModel model){
        RawModel rawModel = model.getRawModel();
        GLES30.glBindVertexArray(rawModel.getVaoID());
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glEnableVertexAttribArray(1);
        ModelTexture texture = model.getTexture();
        shader.loadNumberOfRows(texture.getNumberOfRows());
        if(texture.isHasTransparency()){
            MasterRenderer.disableCulling();
        }
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getTextureID());
    }

    private void unbindTexturedModel(){
        MasterRenderer.enableCulling();
        GLES20.glDisableVertexAttribArray(0);
        GLES20.glDisableVertexAttribArray(1);
        GLES30.glBindVertexArray(0);
    }

    private void prepareInstances(Entity entity){
        float[] transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(),
                entity.getRotation(), entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
        shader.loadOffset(entity.getTextureXOffset(), entity.getTextureYOffset());
    }

    public void cleanUp(){
        shader.cleanUp();
    }

}*/
