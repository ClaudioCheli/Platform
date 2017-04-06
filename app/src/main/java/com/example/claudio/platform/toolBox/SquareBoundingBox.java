package com.example.claudio.platform.toolBox;

import android.util.Log;


/*public class SquareBoundingBox extends BoundingBox{
	
	private Vector2f topLeft;
	private Vector2f bottomRight;
	private Vector2f dimension;
	private Vector3f position;
	
	private final static int[] indices = {
			0,1,
			1,2,
			2,3
	};
	
	public SquareBoundingBox(Loader loader, Vector2f dimension, Vector3f position, boolean active, boolean visible){
		this.active 	= active;
		this.visible	= visible;
		this.dimension 	= dimension;
		this.position 	= position;
		calculateBoundingBox();
		model = loader.loadToVAO(vertices, indices);
		Log.i("point", "SquareBoundingBox: modelLoaded");
	}
	
	public void update(Vector2f delta){
		topLeft.x += delta.x;
		topLeft.y += delta.y;
		
		bottomRight.x += delta.x;
		bottomRight.y += delta.y;
	}
	
	protected void calculateBoundingBox(){
		bottomRight = new Vector2f(position.x, position.y);
		topLeft = new Vector2f(position.x-dimension.x, position.y+dimension.y);
		
		vertices = new float[12];
		vertices[0] = position.x - dimension.x; vertices[ 1] = position.y; 			 	vertices[ 2] = -1;
		vertices[3] = position.x - dimension.x; vertices[ 4] = position.y + dimension.y; vertices[ 5] = -1;
		vertices[6] = position.x;				vertices[ 7] = position.y + dimension.y; vertices[ 8] = -1;
		vertices[9] = position.x;				vertices[10] = position.y;				vertices[11] = -1;
	}
	
	public Vector3f getPosition(){
		return new Vector3f(bottomRight, 0);
	}
	
	public Vector2f getTopLeft(){
		return topLeft;
	}
	public Vector2f getBottomRight(){
		return bottomRight;
	}
}*/
