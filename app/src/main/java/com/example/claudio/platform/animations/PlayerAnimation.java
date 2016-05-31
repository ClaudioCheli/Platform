package com.example.claudio.platform.animations;

import manager.DisplayManager;
import toolBox.Input;

public class PlayerAnimation {

	public static final int WAIT = 0;
	public static final int RUN = 1;
	public static final int TURN = 2;
	public static final int JUMP = 3;
	public static final int SX = 10;
	public static final int DX = 11;
	public static final int RUN_ANIMATION_LENGTH = 8;
	public static final int TURN_ANIMATION_LENGTH = 5;
	public static final int JUMP_ANIMATION_LENGTH = 6;
	private static final int WAIT_DX_FIRST_FRAME = 0;
	private static final int WAIT_SX_FIRST_FRAME = 9;
	private static final int RUN_DX_FIRST_FRAME = 1;
	private static final int RUN_SX_FIRST_FRAME = 10;
	//private static final int JUMP_DX_FIRST_FRAME = 18;
	//private static final int JUMP_SX_FIRST_FRAME = 24;
	private static final int TURN_DX_FIRST_FRAME = 32;
	private static final int TURN_SX_FIRST_FRAME = 40;
	
	private static float frameTime = 80f;
	
	private int direction;
	private int state;
	private int frame;
	private int index;
	private float delta;
	private boolean animation;
	
	public PlayerAnimation(){
		this.direction = DX;
		this.state = WAIT;
		this.frame = 0;
		this.index = 0;
		this.delta = 0;
		this.animation = false;
	}
	
	public void update(){
		switch (state) {
		case WAIT:
			doWait(direction);
			break;
		case RUN:
			doWalk(direction);
			break;
		case TURN:
			doTurn();
			break;
		case JUMP:

			break;
		}
	}
	
	public int move(){
		if(Input.isKeyDown(Input.RIGHT)){
			if(animation == false){
				if(direction == SX){
					animation = true;
					setState(TURN);
				}else{
					animation = true;
					setState(RUN);
					frame = 0;
				}
			}
		}else{
			if(Input.isKeyDown(Input.LEFT)){
				if(animation == false){
					if(direction == DX){
						animation = true;
						setState(TURN);
					}else{
						animation = true;
						setState(RUN);
						frame = 0;
					}
				}
			}else{
				if(Input.isKeyUp(Input.RIGHT)){
					setState(WAIT);
					animation = false;
					if(frame != 0)
						frame = 0;
				}else{
					if(Input.isKeyUp(Input.LEFT)){
						setState(WAIT);
						animation = false;
						if(frame != 9)
							frame = 0;
					}
				}
			}
		}
		return state;
	}
	
	private void doWait(int direction){
		if(direction == DX){
			setIndex(WAIT_DX_FIRST_FRAME);
		}
		if(direction == SX){
			setIndex(WAIT_SX_FIRST_FRAME);
		}
	}
	
	private void doWalk(int direction){
		if((delta = DisplayManager.getFrameTimeSeconds() + delta) >= frameTime){
			switch (direction) {
			case DX:
				setIndex( RUN_DX_FIRST_FRAME+(frame%RUN_ANIMATION_LENGTH) );
				frame++;
				delta = 0;
				break;
			case SX:
				setIndex( RUN_SX_FIRST_FRAME+(frame%RUN_ANIMATION_LENGTH) );
				frame++;
				delta = 0;
				break;
			}
		}
	}
	
	public void doTurn(){
		if((delta = DisplayManager.getFrameTimeSeconds() + delta) >= frameTime){
			if(frame < TURN_ANIMATION_LENGTH){
				switch (this.direction) {
				case DX:
					//Gira da dx a sx
					setIndex(TURN_DX_FIRST_FRAME + frame);
					frame++;
					delta = 0;
					break;
				case SX:
					// gira da sx a dx
					setIndex(TURN_SX_FIRST_FRAME + frame);
					frame++;
					delta = 0;
					break;
				}
			}else{
				animation = false;
				switch (this.direction) {
				case DX:
					setDirection(SX);
					break;
				case SX:
					setDirection(DX);
					break;
				}
			}
		}
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public int getState(){
		return state;
	}
	
	public void setDirection(int direction){
		this.direction = direction;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}
	
}
