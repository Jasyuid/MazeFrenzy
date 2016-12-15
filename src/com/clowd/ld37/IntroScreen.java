package com.clowd.ld37;

import java.awt.event.KeyEvent;

import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.StringObject;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.tile.Tile;
import com.clowd.ld37.sound.Sound;

public class IntroScreen {
	
	private StateManager manager;
	
	private int color;
	private double colorTimer;
	
	public IntroScreen(StateManager manager){
		this.manager = manager;
		color = 0xcc11aa;
		colorTimer = 0;
	}
	
	public void update(){
		if(Keyboard.keyTyped(KeyEvent.VK_ENTER)){
			manager.setState(1);
			Sound.enter.play(false);
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
		screen.renderRect(0, 0, screen.getWidth(), screen.getHeight(), 0x232323);
		screen.addText(new StringObject("PRESS ENTER TO CONTINUE", 50, 250, 60, color));
	}
	
}
