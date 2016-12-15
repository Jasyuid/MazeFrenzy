package com.clowd.ld37.menu;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.clowd.ld37.StateManager;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.gfx.StringObject;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.Level1;
import com.clowd.ld37.sound.Sound;

public class Menu {

	private StateManager manager;
	
	private int bgy = 0;
	private int selected = 0;
	private int state = 0;
	private int prevstate = 0;
	
	private int menuX, menuY;
	private int targetX, targetY;
	
	private int color;
	private double colorTimer;
	
	public Menu(StateManager manager){
		this.manager = manager;
		menuX = 0;
		menuY = 0;
		color = 0xcc11aa;
		colorTimer = 0;
	}
	
	public void update(){
		bgy--;
		if(bgy <= -700*2) bgy = 0;
		
		if(state == 0){
			if(Keyboard.keyTyped(KeyEvent.VK_DOWN) && selected < 1){
				selected++;
			}
			if(Keyboard.keyTyped(KeyEvent.VK_UP) && selected > 0){
				selected--;
			}
			if(Keyboard.keyTyped(KeyEvent.VK_ENTER)){
				if(selected == 0){
					manager.setLevel(new Level1("/levels/level1.png", manager));
					manager.setState(2);
					Sound.enter.play(false);
				}else if(selected == 1){
					Sound.enter.play(false);
					state = 1;
					targetX = 1100;
					prevstate = 0;
				}
			}
		}else if(state == 1){
			if(Keyboard.keyTyped(KeyEvent.VK_ENTER)){
				if(selected == 0){
					Sound.enter.play(false);
					state = 0;
					targetX = 0;
					selected = 0;
					prevstate = 1;
				}
			}
		}
		
		if(menuX > 400) selected = 0;
		
		if(targetX > menuX){
			if(menuX >= targetX /2){
				menuX += (targetX - menuX) / 3;
			}else{
				if(menuX == 0) menuX += 2;
				else menuX *= 1.5;
			}
			
			if(menuX > targetX){
				menuX = targetX;
			}
		}else if(targetX < menuX){
			if(menuX <= targetX /2){
				menuX -= (menuX) / 3;
			}else{
				if(menuX == 1100) menuX-= 2;
				else menuX = 1100 - (int)((1100 - menuX)*1.5);
			}
			
			if(menuX < targetX) menuX = targetX;
		}
		
		colorTimer++;
		if(colorTimer < 50){
			color = ((int)(0xcc11aa + 1.05*colorTimer));
		}else if(colorTimer < 150){
			color = ((int)(0xcc11aa + (100-colorTimer)*1.05));
		}else if(colorTimer < 200){
			color = ((int)(0xcc11aa + (colorTimer-200)*1.05));
		}else{
			colorTimer = 0;
		}
		
	}
	
	public void render(Screen screen){
		screen.renderRect(0, bgy, screen.getWidth(), 300, 0x232323);
		screen.renderRect(0, bgy+screen.getHeight()/3, screen.getWidth(), screen.getHeight()/3, 0x3F3F3F);
		screen.renderRect(0, bgy+screen.getHeight()*2/3, screen.getWidth(), screen.getHeight()/3+1, 0x232323);
		screen.renderRect(0, bgy+screen.getHeight(), screen.getWidth(), screen.getHeight()/3, 0x3F3F3F);
		screen.renderRect(0, bgy+screen.getHeight()*4/3, screen.getWidth(), screen.getHeight()/3, 0x232323);
		screen.renderRect(0, bgy+screen.getHeight()*5/3, screen.getWidth(), screen.getHeight()/3+1, 0x3F3F3F);
		screen.renderRect(0, bgy+screen.getHeight()*2, screen.getWidth(), screen.getHeight()/3, 0x232323);
		screen.renderRect(0, bgy+screen.getHeight()*7/3, screen.getWidth(), screen.getHeight()/3, 0x3F3F3F);
		screen.renderRect(0, bgy+screen.getHeight()*8/3, screen.getWidth(), screen.getHeight()/3+1, 0x232323);
		
		//Main Menu
		
		if(menuX < 1100){
			for(int i = 0; i < Sprite.title.getPixels().length; i++){
				if(Sprite.title.getPixels()[i] == 0xff000000){
					Sprite.title.setPixels(i, color);
				}else if(Sprite.title.getPixels()[i] > color-4 && Sprite.title.getPixels()[i] < color + 4){
					Sprite.title.setPixels(i, color);
				}
			}
			screen.renderTexture(Sprite.title, 85-menuX, 10);
			
			if(selected == 0){
				screen.addText(new StringObject("PLAY", 490-menuX, 420, 40, color));
				screen.addText(new StringObject("ABOUT", 470-menuX, 470, 40, Color.WHITE));
			}else if(selected == 1){
				screen.addText(new StringObject("PLAY", 490-menuX, 420, 40, Color.WHITE));
				screen.addText(new StringObject("ABOUT", 470-menuX, 470, 40, color));
			}
			screen.addText(new StringObject("Made by Protrance", 10-menuX, 690, 20, Color.WHITE));
		}
		
		//About Menu
		int xOff = -menuX+1100;
		
		if(state == 1 || (prevstate == 1 && menuX > 0)){
			screen.addText(new StringObject("ABOUT", 340 + xOff, 100, 100, Color.WHITE));
			screen.addText(new StringObject("MENU", 460 + xOff, 680, 40, color));
			
			screen.addText(new StringObject("\"Maze Frenzy\" was a game made for Ludum dare 37, a game programming event where", 60 + xOff, 250, 20, Color.WHITE));
			screen.addText(new StringObject(" you are given 48 hours to create a game based on a specific theme. This year the", 60 + xOff, 280, 20, Color.WHITE));
			screen.addText(new StringObject("theme was \"One Room\".", 60 + xOff, 310, 20, Color.WHITE));
			
			screen.addText(new StringObject("In the game, your goal is to make your way through a maze to get out of the room", 60 + xOff, 370, 20, Color.WHITE));
			screen.addText(new StringObject("as fast as possible. However you will only be able to see the area next to your", 60 + xOff, 400, 20, Color.WHITE));
			screen.addText(new StringObject("player without the aid of a light powerup. In the mazes there are also various", 60 + xOff, 430, 20, Color.WHITE));
			screen.addText(new StringObject("enemies that will block you and traps that you must deactivat to continue. Can", 60 + xOff, 460, 20, Color.WHITE));
			screen.addText(new StringObject("you make it through all 12 rooms?", 60 + xOff, 490, 20, Color.WHITE));
		}
	}
	
}
