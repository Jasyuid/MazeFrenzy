package com.clowd.ld37.level;

import java.awt.event.KeyEvent;

import com.clowd.ld37.StateManager;
import com.clowd.ld37.entity.item.Item;
import com.clowd.ld37.entity.mob.Player;
import com.clowd.ld37.entity.mob.Walker;
import com.clowd.ld37.entity.trap.Trap;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.gfx.StringObject;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.tile.Tile;
import com.clowd.ld37.sound.Sound;

public class Level7 extends Level{

	public Level7(String path, StateManager manager){
		super(path, manager);
	}
	
	public void generateLevel(){
		levelNum = 7;
		startTimer = 150;
		
		spawnX = levelX + 30*7+5;
		spawnY = levelY + 30*15+5;
		entities.add(new Player(this, spawnX, spawnY));
		
		entities.add(new Item(this, levelX + 30*20+7, levelY + 30*14+7, 0));
		entities.add(new Item(this, levelX + 30*16+7, levelY + 30*9+7, 1));
		entities.add(new Item(this, levelX + 30*11+7, levelY + 30*3+7, 2));
		entities.add(new Item(this, levelX + 30*11+7, levelY + 30*9+7, 2));
		entities.add(new Item(this, levelX + 30*14+7, levelY + 30*7+7, 2));
		
		entities.add(new Trap(this, levelX+30*17, levelY+30*8, 0));
		entities.add(new Trap(this, levelX+30*22, levelY+30*7, 0));
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
				manager.setLevel(new Level8("/levels/level8.png", manager));
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
			
			screen.addText(new StringObject("Traps: ", 70+startY, 210+startX, 50, Tile.walltile.getColor()));
			screen.addText(new StringObject("-  Traps can't be seen and send you back to the", 80+startY, 260+startX, 30, 0xffffff));
			screen.addText(new StringObject("start every time they are touched", 80+startY, 300+startX, 30, 0xffffff));
			screen.renderRect(980+startY, 235+startX, 50, 50, 0x00dddd);
			
			screen.addText(new StringObject("- Deacitivate traps by pressing space next to them", 80+startY, 370+startX, 30, 0xffffff));
			screen.addText(new StringObject("- If there is not trap nearby, you will go back to start", 80+startY, 405+startX, 30, 0xffffff));
			screen.renderRect(980+startY, 335+startX, 50, 50, 0x006666);
			
			screen.addText(new StringObject("- Collect a sensor to see traps", 80+startY, 440+startX, 30, 0xffffff));
			screen.renderTexture(Sprite.trapsensor, 610+startY, 423+startX);
			
			screen.addText(new StringObject("Press Enter to Begin", 240+startY, 520+startX, 50, 0xffffff));
		}
		if(end){
			screen.renderRect(40+endY, 140+endX, 1020, 420, Tile.walltile.getColor());
			screen.renderRect(50+endY, 150+endX, 1000, 400, 0x333333);
			
			screen.addText(new StringObject("Final Time: " + timeS + "." + timeMS, 310+endY, 210+endX, 50, Tile.walltile.getColor()));
			
			screen.addText(new StringObject("Rank: ", 320+endY, 380+endX, 100, 0xffffff));
			if(timeS < 28){
				screen.addText(new StringObject("A", 650+endY, 380+endX, 100, 0x00dd00));
				Level.ratings[levelNum] = "A";
			}else if(timeS < 38){
				screen.addText(new StringObject("B", 650+endY, 380+endX, 100, 0x2222dd));
				Level.ratings[levelNum] = "B";
			}else if(timeS < 50){
				screen.addText(new StringObject("C", 650+endY, 380+endX, 100, 0xdddd00));
				Level.ratings[levelNum] = "C";
			}else if(timeS < 70){
				screen.addText(new StringObject("D", 650+endY, 380+endX, 100, 0xdd8800));
				Level.ratings[levelNum] = "D";
			}else{
				screen.addText(new StringObject("F", 650+endY, 380+endX, 100, 0xdd0000));
				Level.ratings[levelNum] = "F";
			}
			
			screen.addText(new StringObject("Press Enter to Continue", 200+endY, 520+endX, 50, 0xffffff));
		}
	}
	
}
