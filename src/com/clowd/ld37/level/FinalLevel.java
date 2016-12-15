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

public class FinalLevel extends Level{
	
	private int selected = 0;
	
	public FinalLevel(String path, StateManager manager){
		super(path, manager);
	}
	
	public void generateLevel(){
		
	}
	
	public void update2(){
		if(Keyboard.keyTyped(KeyEvent.VK_RIGHT) && selected < 1){
			selected++;
		}
		if(Keyboard.keyTyped(KeyEvent.VK_LEFT) && selected > 0){
			selected--;
		}
		if(Keyboard.keyTyped(KeyEvent.VK_ENTER)){
			if(selected == 0){
				manager.setState(1);
				Sound.enter.play(false);
			}else if(selected == 1){
				start = false;
				Sound.enter.play(false);
			}
		}
		if(startX >= 1000){
			manager.setLevel(new Level1("/levels/level1.png", manager));
		}
	}
	
	public int setRatingColor(int l){
		String s = ratings[l];
		if(s == "A"){
			return 0x00dd00;
		}else if(s == "B"){
			return 0x2222dd;
		}else if(s == "C"){
			return 0xdddd00;
		}else if(s == "D"){
			return 0xdd8800;
		}else if(s == "F"){
			return 0xdd0000;
		}
		return 0xffffff;
	}
	
	public void render2(Screen screen){
		if(start || startX < 1000){
			screen.renderRect(40+startY, 40+startX, 1020, 620, Tile.walltile.getColor());
			screen.renderRect(50+startY, 50+startX, 1000, 600, 0x333333);
			
			screen.addText(new StringObject("Ratings", 430+startY, 100+startX, 50, Tile.walltile.getColor()));
			
			screen.addText(new StringObject("1 - ", 380+startY, 220+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[1], 470+startY, 220+startX, 40, setRatingColor(1)));
			
			screen.addText(new StringObject("2 - ", 380+startY, 270+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[2], 470+startY, 270+startX, 40, setRatingColor(2)));
			
			screen.addText(new StringObject("3 - ", 380+startY, 320+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[3], 470+startY, 320+startX, 40, setRatingColor(3)));
			
			screen.addText(new StringObject("4 - ", 380+startY, 370+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[4], 470+startY, 370+startX, 40, setRatingColor(4)));
			
			screen.addText(new StringObject("5 - ", 380+startY, 420+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[5], 470+startY, 420+startX, 40, setRatingColor(5)));
			
			screen.addText(new StringObject("6 - ", 380+startY, 470+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[6], 470+startY, 470+startX, 40, setRatingColor(6)));
			
			screen.addText(new StringObject("7 - ", 600+startY, 220+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[7], 690+startY, 220+startX, 40, setRatingColor(7)));
			
			screen.addText(new StringObject("8 - ", 600+startY, 270+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[8], 690+startY, 270+startX, 40, setRatingColor(8)));
			
			screen.addText(new StringObject("9 - ", 600+startY, 320+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[9], 690+startY, 320+startX, 40, setRatingColor(9)));
			
			screen.addText(new StringObject("10 - ", 600+startY, 370+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[10], 690+startY, 370+startX, 40, setRatingColor(10)));
			
			screen.addText(new StringObject("11 - ", 600+startY, 420+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[11], 690+startY, 420+startX, 40, setRatingColor(11)));
			
			screen.addText(new StringObject("12 - ", 600+startY, 470+startX, 40, 0xffffff));
			screen.addText(new StringObject(ratings[12], 690+startY, 470+startX, 40, setRatingColor(12)));
			
			if(selected == 0){
				screen.addText(new StringObject("MENU", 200+startY, 635+startX, 40, Tile.walltile.getColor()));
				screen.addText(new StringObject("PLAY AGAIN", 640+startY, 635+startX, 40, 0xffffff));
			}else if(selected == 1){
				screen.addText(new StringObject("MENU", 200+startY, 635+startX, 40, 0xffffff));
				screen.addText(new StringObject("PLAY AGAIN", 640+startY, 635+startX, 40, Tile.walltile.getColor()));
			}
			
		}
	}
	
}
