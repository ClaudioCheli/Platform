package com.example.claudio.platform.toolBox;

public class Vector3f {

	public float x,y,z;

	public Vector3f(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3f(Vector2f vec, float z){
		this.x = vec.x;
		this.y = vec.y;
		this.z = z;
	}

	public Vector3f(Vector3f vec){
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}
	
	public static float distance(Vector3f vec1, Vector3f vec2){
		return (float) Math.sqrt(vec1.x*vec2.x + vec1.y*vec2.y + vec1.z*vec2.z);
	}

}
