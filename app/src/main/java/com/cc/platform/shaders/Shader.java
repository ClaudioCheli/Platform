package com.cc.platform.shaders;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;
import com.cc.platform.toolBox.Vector3f;
import com.cc.platform.toolBox.Vector4f;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Claudio on 28/05/2016.
 */
public abstract class Shader {

    private final int programID;
    private final int vertexShaderID;
    private final int fragmentShaderID;

    public Shader(int vertexFile, int fragmentFile, Context context) {
        vertexShaderID = loadShader(vertexFile, GLES20.GL_VERTEX_SHADER, context);
        fragmentShaderID = loadShader(fragmentFile, GLES20.GL_FRAGMENT_SHADER, context);
        Util.checkError();
        programID = GLES30.glCreateProgram();
        Util.checkError();
        GLES30.glAttachShader(programID, vertexShaderID);
        GLES30.glAttachShader(programID, fragmentShaderID);
        Util.checkError();
        bindAttributes();
        GLES30.glLinkProgram(programID);
        Util.checkError();

        // Informazioni sulla compilazione e linking degli shaders e del program
        GLES30.glValidateProgram(programID);
        String programInfo = GLES30.glGetProgramInfoLog(programID);
        String vertexInfo = GLES30.glGetShaderInfoLog(vertexShaderID);
        String fragmentInfo = GLES30.glGetShaderInfoLog(fragmentShaderID);
        Util.checkError();
        Log.i("shader", "vertexShader: " + vertexInfo);
        Log.i("shader", "fragmentShader: " + fragmentInfo);
        Log.i("shader", "program: " + programInfo);

        getAllUniformLocations();
    }

    public void start() {
        GLES20.glUseProgram(programID);
    }

    public void stop() {
        GLES20.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GLES20.glDetachShader(programID, vertexShaderID);
        GLES20.glDetachShader(programID, fragmentShaderID);
        GLES20.glDeleteShader(vertexShaderID);
        GLES20.glDeleteShader(fragmentShaderID);
        GLES20.glDeleteProgram(programID);
    }

    public int getProgramID() {
        return programID;
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName) {
        //Log.i("error", "program " + programID + " uniform " + uniformName);
        return GLES30.glGetUniformLocation(programID, uniformName);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName) {
        GLES20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected void loadInt(int location, int value) {
        GLES20.glUniform1i(location, value);
    }


    protected void loadFloat(int location, float value) {
        GLES20.glUniform1f(location, value);
    }

    protected void loadVector4f(int location, Vector4f vector) {
        GLES20.glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
    }

    protected void loadVector3f(int location, Vector3f vector) {
        GLES20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void loadVector2f(int location, Vector2f vector) {
        GLES20.glUniform2f(location, vector.x, vector.y);
    }

    protected void loadBoolean(int location, boolean value) {
        float toLoad = 0;
        if (value) {
            toLoad = 1;
        }
        GLES20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, float[] matrix) {
        ByteBuffer pointVBB = ByteBuffer.allocateDirect(matrix.length * 4);
        pointVBB.order(ByteOrder.nativeOrder());
        FloatBuffer matrixBuffer = pointVBB.asFloatBuffer();
        matrixBuffer.put(matrix);
        matrixBuffer.position(0);
        GLES20.glUniformMatrix4fv(location, 1, false, matrixBuffer);
    }

    private int loadShader(int fileId, int type, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        try {
            InputStream inputStream = context.getResources().openRawResource(fileId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String shader = stringBuilder.toString();
        int shaderID = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shaderID, shader);
        GLES30.glCompileShader(shaderID);
        return shaderID;
    }
}
