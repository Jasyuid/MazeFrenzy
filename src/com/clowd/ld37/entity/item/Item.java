package com.clowd.ld37.entity.item;

import java.util.Random;

import com.clowd.ld37.entity.Entity;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.level.Level;

public class Item extends Entity{

	protected int type;
	
	//Create item and set type
	public Item(Level level, double x, double y, int type){
		super(level, x, y);
		if(type == 0){
			sprite = Sprite.lightbomb;
		}else if(type == 1){
			sprite = Sprite.lightball;
		}else if(type == 2){
			sprite = Sprite.trapsensor;
		}
		this.type = type;
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	public void update(){

	}
	
	//Render sprite associated with item
	public void render(Screen screen){
		screen.renderTexture(sprite, (int)x, (int)y);
	}
	
	public int getType(){
		return type;
	}
	
}
