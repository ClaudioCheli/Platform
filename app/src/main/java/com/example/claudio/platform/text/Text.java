package com.example.claudio.platform.text;

import android.opengl.Matrix;

import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector4f;


/**
 * Created by Claudio on 21/09/2017.
 */

public class Text{

    private String text;
    private Vector2f position;
    private Vector4f color;
    private Font font;

    private float[] modelMatrix = new float[16];

    public Text(String text, Vector2f position, Vector4f color, Font font) {
        this.text = text;
        this.position = position;
        this.font = font;
        this.color = color;

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, position.x, position.y, -1);
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }

    public float[] getModelMatrix() {
        return modelMatrix;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getLength() {return text.length();}
}
