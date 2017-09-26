package com.example.claudio.platform.textures;

import com.example.claudio.platform.main.MainActivity;
import com.example.claudio.platform.toolBox.Util;
import com.example.claudio.platform.toolBox.Vector2f;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture {

	//private static int call = 0;

	private int textureID;
	private Vector2f dimension;

	public Texture(int resourceID){
        int[] textureHandle = new int[1];

		GLES20.glGenTextures(1, textureHandle,0);

		if(textureHandle[0] != 0){
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inScaled = false;

			final Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.context.getResources(), resourceID, options);
			dimension = new Vector2f(bitmap.getHeight(), bitmap.getWidth());

			GLES20.glActiveTexture(GLES20.GL_TEXTURE0);

			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);


			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
			//Log.i("error", "call " + call); call++;
			Util.checkError();
			bitmap.recycle();
		}

		if (textureHandle[0] == 0) {
			throw new RuntimeException("Error loading texture.");
		}

		this.textureID = textureHandle[0];

	}

	public Texture(Bitmap bitmap) {
		int[] textureHandle = new int[1];

		GLES20.glGenTextures(1, textureHandle, 0);
		dimension = new Vector2f(bitmap.getHeight(), bitmap.getWidth());

		if(textureHandle[0] != 0) {
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

			bitmap.recycle();
		}

		if(textureHandle[0] == 0) {
			throw new RuntimeException("Error loading texture.");
		}

		this.textureID = textureHandle[0];
	}

	public int getTextureID(){return textureID;}


}
