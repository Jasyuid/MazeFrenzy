package com.clowd.ld37.entity.mob;

import java.awt.event.KeyEvent;

import com.clowd.ld37.entity.Entity;
import com.clowd.ld37.entity.item.Item;
import com.clowd.ld37.entity.trap.Trap;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.Level;
import com.clowd.ld37.sound.Sound;

public class Player extends Mob{

	private double xa, ya;
	
	//Create player and get dimensions
	public Player(Level level, double x, double y){
		super(level ,x, y);
		sprite = sprite.player;
		width = sprite.getWidth();
		height = sprite.getHeight();
		speed = 1.5;
	}
	
	public void update(){
		//Move player
		xa = 0; ya = 0;
		
		if(Keyboard.keyPressed(KeyEvent.VK_UP) || Keyboard.keyPressed(KeyEvent.VK_W)) ya-=speed;
		if(Keyboard.keyPressed(KeyEvent.VK_DOWN) || Keyboard.keyPressed(KeyEvent.VK_S)) ya+=speed;
		if(Keyboard.keyPressed(KeyEvent.VK_LEFT) || Keyboard.keyPressed(KeyEvent.VK_A)) xa-=speed;
		if(Keyboard.keyPressed(KeyEvent.VK_RIGHT) || Keyboard.keyPressed(KeyEvent.VK_D)) xa+=speed;

		if(xa != 0 || ya != 0){
			move(xa, ya);
		}
		level.setPlayerX(x);
		level.setPlayerY(y);
		
		//Get entity collisions
		for(Entity e : level.getEntites()){
			if((e.getX() < x + width && e.getX() + e.getWidth() > x) && (e.getY() < y + height && e.getY() + e.getHeight() > y)){
				if(e instanceof Item){
					if(((Item) e).getType() == 0){
						Sound.powerup.play(false);
						level.activateLightBomb();
						level.removeEntity(e);
					}else if(((Item) e).getType() == 1){
						Sound.powerup.play(false);
						level.activateLightBall();
						level.removeEntity(e);
					}else if(((Item) e).getType() == 2){
						Sound.powerup.play(false);
						level.activateTrapSensor();
						level.removeEntity(e);
					}
				}else if(e instanceof Walker){
					x = level.getSpawnX();
					y = level.getSpawnY();
					Sound.hit.play(false);
					level.activateLightBomb();
				}else if(e instanceof Trap){
					if(((Trap)e).getOn()){
						x = level.getSpawnX();
						y = level.getSpawnY();
						Sound.hit.play(false);
						level.activateLightBomb();
					}
				}
			}
		}
		
		//Respawn code
		if(level.getRespawn()){
			level.setRespawn(false);
			x = level.getSpawnX();
			y = level.getSpawnY();
			Sound.hit.play(false);
			level.activateLightBomb();
		}
		
	}
	
	//Render sprite
	public void render(Screen screen){
		screen.renderTexture(sprite,(int) x, (int)y);
	}
	
	public double getPlayerX(){
		return x;
	}
	public double getPlayerY(){
		return y;
	}
	public void setPlayerX(double x){
		this.x = x;
	}
	public void setPlayerY(double y){
		this.y = y;
	}
}
