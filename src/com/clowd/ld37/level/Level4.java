package com.clowd.ld37.level;

import java.awt.event.KeyEvent;

import com.clowd.ld37.StateManager;
import com.clowd.ld37.entity.item.Item;
import com.clowd.ld37.entity.mob.Player;
import com.clowd.ld37.entity.mob.Walker;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.gfx.StringObject;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.tile.Tile;
import com.clowd.ld37.sound.Sound;

public class Level4 extends Level{

	public Level4(String path, StateManager manager){
		super(path, manager);
	}
	
	public void generateLevel(){
		levelNum = 4;
		startTimer = 100;
		
		spawnX = levelX + 30*7+5;
		spawnY = levelY + 30*5+5;
		entities.add(new Player(this, spawnX, spawnY));
		
		entities.add(new Item(this, levelX + 30*15+7, levelY + 30*12+7, 1));
		entities.add(new Item(this, levelX + 30*19+7, levelY + 30*12+7, 0));
		
		entities.add(new Walker(this, levelX+30*12+3, levelY+30*10+3, 0, 2, 30*4));
		entities.add(new Walker(this, levelX+30*12+3, levelY+30*8+3, 0, 3, 30*5));
	}
	
	public void update2(){
		if(start){
			if(Keyboard.keyTyped(KeyEvent.VK_ENTER)){
				start = false;
				Sound.enter.play(false);
				Sound.music.play(true);
			}
		}else if(end){
			if(Keyboard.keyTyped(KeyEvent.VK_ENTER)){
				nextLevel = true;
				Sound.enter.play(false);
			}
			if(endY <= -1200){
				manager.setLevel(new Level5("/levels/level5.png", manager));
			}
		}else{
			updateEntities();
			if(tiles[((int)(playerX-levelX+10)/30)+((int)(playerY-levelY+10)/30)*width] == 0xff0000ff){
				end = true;
				Sound.finish.play(false);
				Sound.music.stop();
			}
		}
	}
	
	public void render2(Screen screen){
		if(start || startX < 1000){
			screen.renderRect(40+startY, 140+startX, 1020, 420, Tile.walltile.getColor());
			screen.renderRect(50+startY, 150+startX, 1000, 400, 0x333333);
			
			screen.addText(new StringObject("Enemies: ", 70+startY, 210+startX, 50, Tile.walltile.getColor()));
			screen.addText(new StringObject("Send you back to the start if touched", 80+startY, 260+startX, 30, 0xffffff));
			
			screen.addText(new StringObject("\"Walker\" - Moves back and forth", 80+startY, 320+startX, 30, 0xffffff));
			screen.renderTexture(Sprite.walkera, 237+startY, 370+startX);
			screen.renderTexture(Sprite.walkerb, 537+startY, 370+startX);
			screen.renderTexture(Sprite.walkerc, 837+startY, 370+startX);
			screen.addText(new StringObject("Slow", 210+startY, 430+startX, 30, 0xffffff));
			screen.addText(new StringObject("Medium", 490+startY, 430+startX, 30, 0xffffff));
			screen.addText(new StringObject("Fast", 810+startY, 430+startX, 30, 0xffffff));
			
			screen.addText(new StringObject("Press Enter to Begin", 240+startY, 520+startX, 50, 0xffffff));
		}
		if(end){
			screen.renderRect(40+endY, 140+endX, 1020, 420, Tile.walltile.getColor());
			screen.renderRect(50+endY, 150+endX, 1000, 400, 0x333333);
			
			screen.addText(new StringObject("Final Time: " + timeS + "." + timeMS, 310+endY, 210+endX, 50, Tile.walltile.getColor()));
			
			screen.addText(new StringObject("Rank: ", 320+endY, 380+endX, 100, 0xffffff));
			if(timeS < 22){
				screen.addText(new StringObject("A", 650+endY, 380+endX, 100, 0x00dd00));
				ratings[levelNum] = "A";
			}else if(timeS < 30){
				screen.addText(new StringObject("B", 650+endY, 380+endX, 100, 0x2222dd));
				ratings[levelNum] = "B";
			}else if(timeS < 36){
				screen.addText(new StringObject("C", 650+endY, 380+endX, 100, 0xdddd00));
				ratings[levelNum] = "C";
			}else if(timeS < 50){
				screen.addText(new StringObject("D", 650+endY, 380+endX, 100, 0xdd8800));
				ratings[levelNum] = "D";
			}else{
				screen.addText(new StringObject("F", 650+endY, 380+endX, 100, 0xdd0000));
				ratings[levelNum] = "F";
			}
			
			screen.addText(new StringObject("Press Enter to Continue", 200+endY, 520+endX, 50, 0xffffff));
		}
	}
	
}
