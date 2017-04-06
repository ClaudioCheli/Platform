package com.example.claudio.platform.toolBox;

import android.opengl.Matrix;


/**
 * Created by clasb on 28/05/2016.
 */
/*public class Maths {

    public static float[] createTransformationMatrix(Vector3f translation, Vector3f rotation, Vector3f scale){
        float[] matrix = new float[16];
        Matrix.setIdentityM(matrix, 0);
        Matrix.translateM(matrix, 0, translation.x, translation.y, translation.z);
        Matrix.rotateM(matrix, 0, (float)Math.toRadians(rotation.x), 1, 0, 0);
        Matrix.rotateM(matrix, 0, (float)Math.toRadians(rotation.y), 0, 1, 0);
        Matrix.rotateM(matrix, 0, (float)Math.toRadians(rotation.z), 0, 0, 1);
        Matrix.scaleM(matrix, 0, scale.x, scale.y, scale.z);
        return matrix;
    }

    public static float[] createViewMatrix(Camera camera){
        float[] matrix = new float[16];
        Matrix.setIdentityM(matrix, 0);
        Matrix.rotateM(matrix, 0, (float)Math.toRadians(camera.getPitch()), 1, 0, 0);
        Matrix.rotateM(matrix, 0, (float)Math.toRadians(camera.getYaw()), 0, 1, 0);
        Matrix.rotateM(matrix, 0, (float)Math.toRadians(camera.getRoll()), 0, 0, 1);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix.translateM(matrix, 0, negativeCameraPos.x, negativeCameraPos.y, negativeCameraPos.z);
        return matrix;
    }
}*/
