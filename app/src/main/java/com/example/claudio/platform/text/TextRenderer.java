package com.example.claudio.platform.text;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.example.claudio.platform.camera.Camera;
import com.example.claudio.platform.shaders.TextShader;
import com.example.claudio.platform.textures.Texture;
import com.example.claudio.platform.toolBox.Vector2f;

import java.util.List;

/**
 * Created by Claudio on 22/09/2017.
 */

public class TextRenderer {

    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 100f;

    private float[] projectionMatrix = new float[16];

    public final static int CHAR_BATCH_SIZE = 24;


    private TextShader shader;
    private TextBatch batch;

    public TextRenderer() {
        this.shader = new TextShader();

        batch = new TextBatch(CHAR_BATCH_SIZE);
    }

    public void begin(){
        shader.start();

    }

    public void render(List<Text> texts, Camera camera){
        shader.loadProjectionMatrix(projectionMatrix);
        shader.loadViewMatrix(camera.getViewMatrix());
        for(Text text : texts) {
            batch.beginBatch();
            bindTexture(text.getFont().getTexture());
            shader.loadModelMatrix(text.getModelMatrix());
            shader.loadColor(text.getColor());
            Font font = text.getFont();
            float letterX = 0, letterY = 0;
            int c;
            for(int i=0; i<text.getLength(); i++) {
                c = (int) text.getText().charAt(i) - Font.CHAR_START;
                if(c < 0 || c >= Font.CHAR_CNT)
                    c = Font.CHAR_UNKNOWN;
                batch.drawSprite(new Vector2f(letterX, letterY), font.getDimension(), font.getRegion(c));
                letterX += (font.charWidths[c] + font.spaceX) * font.scaleX;
            }
            batch.endBatch();
        }
    }

    public void end(){

        shader.stop();
    }

    public void setProjectionMatrix(int width, int height){
        Matrix.orthoM(projectionMatrix, 0, 0, width, height, 0, NEAR_PLANE, FAR_PLANE);
    }

    private void bindTexture(Texture texture) {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.getTextureID());
    }


}
