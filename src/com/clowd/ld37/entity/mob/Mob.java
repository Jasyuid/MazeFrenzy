package com.clowd.ld37.entity.mob;

import com.clowd.ld37.entity.Entity;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.level.Level;

public class Mob extends Entity{

	protected double speed;
	
	public Mob(Level level, double x, double y){
		super(level, x, y);
	}
	
	public Mob(Level level, double x, double y, int type){
		super(level, x, y, type);
	}
	
	public Mob(Level level){
		super(level);
	}
	
	public void move(double xa, double ya){
		if(xa!=0 && ya!=0){
			move(xa*0.71, 0);
			move(0, ya*0.71);
			return;
		}
		
		while(xa != 0){
			if(Math.abs(xa) > 1){
				if(!tileCollision(abs(xa), ya)){
					this.x += abs(xa);
				}
				xa -= abs(xa);
			}else{
				if(!tileCollision(abs(xa), ya)){
					this.x += xa;
				}
				xa = 0;
			}
		}
		
		while(ya != 0){
			if(Math.abs(ya) > 1){
				if(!tileCollision(xa, abs(ya))){
					this.y += abs(ya);
				}
				ya -= abs(ya);
			}else{
				if(!tileCollision(xa, abs(ya))){
					this.y += ya;
				}
				ya = 0;
			}
		}
	}
	
	public double abs(double val){
		if(val > 0) return 1;
		return -1;
	}
	
	public boolean tileCollision(double xa, double ya){
		boolean solid = false;
		for(int c = 0; c < 4; c++){
			double xt = (((x + xa) + c % 2 * 19) - level.getLevelX()) / 30;
			double yt = (((y + ya) + c / 2 * 19) - level.getLevelY()) / 30;
			if(level.getTile((int)xt, (int)yt).isSolid()) solid = true;
		}
		return solid;
	}
	
	public void update(){
		
	}
	
	public void render(Screen screen){
		
	}
	
}
