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

public class Level5 extends Level{

	public Level5(String path, StateManager manager){
		super(path, manager);
	}
	
	public void generateLevel(){
		levelNum = 5;
		startTimer = 100;
		
		spawnX = levelX + 30*9+5;
		spawnY = levelY + 30*4+5;
		entities.add(new Player(this, spawnX, spawnY));
		
		entities.add(new Item(this, levelX + 30*9+7, levelY + 30*10+7, 0));
		entities.add(new Item(this, levelX + 30*19+7, levelY + 30*10+7, 1));
		
		entities.add(new Walker(this, levelX+30*13+3, levelY+30*6+3, 1, 1, 30*4));
		entities.add(new Walker(this, levelX+30*15+3, levelY+30*10+3, 1, 3, 30*2));
		entities.add(new Walker(this, levelX+30*13+3, levelY+30*12+3, 1, 1, 30*4));
		entities.add(new Walker(this, levelX+30*19+3, levelY+30*5+3, 1, 1, 30*2));
		entities.add(new Walker(this, levelX+30*18+3, levelY+30*14+3, 2, 3, 30*10));
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
				manager.setLevel(new Level6("/levels/level6.png", manager));
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
			
			screen.addText(new StringObject("Press Enter to Begin", 240+startY, 320+startX, 50, 0xffffff));
		}
		if(end){
			screen.renderRect(40+endY, 140+endX, 1020, 420, Tile.walltile.getColor());
			screen.renderRect(50+endY, 150+endX, 1000, 400, 0x333333);
			
			screen.addText(new StringObject("Final Time: " + timeS + "." + timeMS, 310+endY, 210+endX, 50, Tile.walltile.getColor()));
			
			screen.addText(new StringObject("Rank: ", 320+endY, 380+endX, 100, 0xffffff));
			if(timeS < 30){
				screen.addText(new StringObject("A", 650+endY, 380+endX, 100, 0x00dd00));
				ratings[levelNum] = "A";
			}else if(timeS < 40){
				screen.addText(new StringObject("B", 650+endY, 380+endX, 100, 0x2222dd));
				ratings[levelNum] = "B";
			}else if(timeS < 50){
				screen.addText(new StringObject("C", 650+endY, 380+endX, 100, 0xdddd00));
				ratings[levelNum] = "C";
			}else if(timeS < 60){
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
