package com.clowd.ld37.entity.trap;

import java.awt.event.KeyEvent;

import com.clowd.ld37.entity.Entity;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.Level;

public class Trap extends Entity{

	protected int type;
	
	protected boolean visible = false;
	protected boolean on = true;
	
	//Create trap at coords
	public Trap(Level level, double x, double y, int type){
		super(level, x, y, type);
		if(type==0){
			sprite = Sprite.trapon;
		}
		this.type = type;
		width = sprite.getWidth();
		height = sprite.getHeight();
	}
	
	//Set visibility of traps
	public void update(){
		visible = level.getTrapVision();
		
	}
	
	public void render(Screen screen){
		//Render if trap is on or off
		if(visible && on){
			screen.renderTexture(Sprite.trapon, (int)x, (int)y);
		}else if(!on){
			screen.renderTexture(Sprite.trapoff, (int)x, (int)y);
		}
	}
	
	public boolean getOn(){
		return on;
	}
	
	public void setOn(boolean o){
		on = o;
	}
	
}
