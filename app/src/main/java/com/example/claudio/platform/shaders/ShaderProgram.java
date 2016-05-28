package com.example.claudio.platform.shaders;

import android.opengl.GLES20;

import com.example.claudio.platform.main.MainActivity;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;
import com.example.claudio.platform.toolBox.Vector3f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Claudio on 28/05/2016.
 */
public abstract class ShaderProgram {

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    private static FloatBuffer matrixBuffer = Util.createFloatBuffer(16);

    public ShaderProgram(int vertexFile, int fragmentFile){
        vertexShaderID = loadShader(vertexFile, GLES20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GLES20.GL_FRAGMENT_SHADER);
        programID = GLES20.glCreateProgram();
        GLES20.glAttachShader(programID, vertexShaderID);
        GLES20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GLES20.glLinkProgram(programID);

        // Informazioni sulla compilazione e linking degli shaders e del program
        GLES20.glValidateProgram(programID);
        String programInfo = GLES20.glGetProgramInfoLog(programID);
        String vertexInfo = GLES20.glGetShaderInfoLog(vertexShaderID);
        String fragmentInfo = GLES20.glGetShaderInfoLog(fragmentShaderID);

        getAllUniformLocations();
    }

    public void start(){
        GLES20.glUseProgram(programID);
    }

    public void stop(){
        GLES20.glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        GLES20.glDetachShader(programID, vertexShaderID);
        GLES20.glDetachShader(programID, fragmentShaderID);
        GLES20.glDeleteShader(vertexShaderID);
        GLES20.glDeleteShader(fragmentShaderID);
        GLES20.glDeleteProgram(programID);
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName){
        return GLES20.glGetUniformLocation(programID, uniformName);
    }

    protected abstract void bindAttributes();

    protected void bindAttributes(int attribute, String variableName){
        GLES20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected void loadFloat(int location, float value){
        GLES20.glUniform1f(location, value);
    }

    protected void loadVector(int location, Vector3f vector){
        GLES20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    protected void load2DVector(int location, Vector2f vector){
        GLES20.glUniform2f(location, vector.x, vector.y);
    }

    protected void loadBoolean(int location, boolean value){
        float toLoad = 0;
        if(value){
            toLoad = 1;
        }
        GLES20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, float[] matrix){
        ByteBuffer pointVBB = ByteBuffer.allocateDirect(matrix.length * 4);
        pointVBB.order(ByteOrder.nativeOrder());
        matrixBuffer = pointVBB.asFloatBuffer();
        matrixBuffer.put(matrix);
        matrixBuffer.position(0);
        GLES20.glUniformMatrix4fv(location, 1, false, matrixBuffer);
    }

    private static int loadShader(int fileId, int type){
        InputStream inputStream = MainActivity.context.getResources().openRawResource(fileId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String str;
        String shader = "";
        if(inputStream != null){
            try {
                while( (str = reader.readLine()) != null )
                    shader = shader + str;
            } catch (IOException ioe) {}
        }
        int shaderID = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shaderID, shader);
        GLES20.glCompileShader(shaderID);
        return shaderID;
    }
}
