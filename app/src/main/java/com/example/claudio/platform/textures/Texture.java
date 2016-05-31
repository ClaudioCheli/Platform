package com.example.claudio.platform.textures;

import com.example.claudio.platform.main.MainActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class Texture {

public static int LoadTexture(final int resourceId){
		
		final int[] textureHandle = new int[1];
		
		GLES20.glGenTextures(1, textureHandle, 0);
		
		if(textureHandle[0] != 0){
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inScaled = false;
			
			final Bitmap bitmap = BitmapFactory.decodeResource(MainActivity.context.getResources(), resourceId, options);
			
		    GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
			
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
			
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
	        
	 
	        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
	        
	        bitmap.recycle();
		}
		
		if (textureHandle[0] == 0)
	    {
	        throw new RuntimeException("Error loading texture.");
	    }
		
		return textureHandle[0];
	}
}
