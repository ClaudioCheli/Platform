package com.example.claudio.platform.toolBox;

import com.example.claudio.platform.models.RawModel;

public abstract class BoundingBox {
	
	protected boolean active;
	protected RawModel model;
	protected boolean visible;
	protected float[] vertices;
	
	public abstract void update(Vector2f delta);

	public abstract Vector3f getPosition();
	
	protected abstract void calculateBoundingBox();
	
	public RawModel getRawModel(){
		return model;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public boolean isVisible(){
		return visible;
	}

}
