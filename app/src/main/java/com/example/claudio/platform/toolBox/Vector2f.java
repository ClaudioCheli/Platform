package com.example.claudio.platform.toolBox;

public class Vector2f {
	
	public float x,y;

	public Vector2f(){
		this.x = 0;
		this.y = 0;
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f vec){
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public static float distance(Vector2f vec1, Vector2f vec2){
		return (float) Math.sqrt(vec1.x*vec2.x + vec1.y*vec2.y);
	}
	
}
