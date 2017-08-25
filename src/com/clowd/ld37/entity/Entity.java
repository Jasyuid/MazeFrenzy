package com.clowd.ld37.entity;

import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.interfaces.Renderable;
import com.clowd.ld37.level.Level;

public class Entity{

	protected Level level;
	protected double x, y;
	protected int width, height;
	protected Sprite sprite;
	protected int state;
	protected int type;
	
	//Creates entity at coords
	public Entity(Level level, double x, double y){
		this.level = level;
		this.x = x;
		this.y = y;
		type = 0;
	}
	public Entity(Level level, double x, double y, int type){
		this.level = level;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public Entity(Level level){
		this.level = level;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}

	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}

}
