package com.clowd.ld37.entity.mob;

import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.level.Level;

public class Walker extends Mob{

	private double xa, ya;
	private int dr, rg;
	private double ix, iy;
	
	//Create a walker enemy with a certain direction(dr) and walking range(rg)
	public Walker(Level level, double x, double y, int type, int dr, int rg){
		super(level, x, y, type);
		this.dr = dr;
		this.rg = rg;
		ix = x;
		iy = y;
		if(type==0){
			sprite = Sprite.walkera;
			speed = 0.5;
		}else if(type == 1){
			sprite = Sprite.walkerb;
			speed = 1;
		}else if(type == 2){
			sprite = Sprite.walkerc;
			speed = 1.5;
		}
		width = sprite.getWidth();
		height = sprite.getHeight();
		
	}
	
	public void update(){
		//Move walker back and forth
		xa = 0; ya = 0;
		
		if(dr == 0){
			if(y < iy - rg || y > iy){
				speed = -speed;
			}
			ya-=speed;
		}else if(dr == 1){
			if(x > ix + rg || x < ix){
				speed = -speed;
			}
			xa+=speed;
		}else if(dr == 2){
			if(y > iy + rg || y < iy){
				speed = -speed;
			}
			ya+=speed;
		}else if(dr == 3){
			if(x < ix - rg || x > ix){
				speed = -speed;
			}
			xa-=speed;
		}
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
		}
	}
	//Render sprite
	public void render(Screen screen){
		screen.renderTexture(sprite, (int)x, (int)y);
	}
	
}
