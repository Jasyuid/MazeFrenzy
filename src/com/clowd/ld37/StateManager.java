package com.clowd.ld37;

import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.level.FinalLevel;
import com.clowd.ld37.level.Level;
import com.clowd.ld37.level.Level1;
import com.clowd.ld37.level.Level10;
import com.clowd.ld37.level.Level11;
import com.clowd.ld37.level.Level12;
import com.clowd.ld37.level.Level2;
import com.clowd.ld37.level.Level3;
import com.clowd.ld37.level.Level4;
import com.clowd.ld37.level.Level5;
import com.clowd.ld37.level.Level6;
import com.clowd.ld37.level.Level7;
import com.clowd.ld37.level.Level8;
import com.clowd.ld37.level.Level9;
import com.clowd.ld37.menu.Menu;

public class StateManager {

	private int state;
	
	private boolean pause;
	
	private Game game;
	private IntroScreen intro;
	private Menu menu;
	private Level level;
	//Background coords
	protected double bgX, bgY;
	
	public StateManager(Game game){
		state = 0;
		pause = false;
		this.game = game;
		
		intro = new IntroScreen(this);
		menu = new Menu(this);
		level = new Level1("/levels/level1.png", this);
		bgX = 0;
		bgY = 0;
	}
	
	public void quit(){
		game.stop();
	}
	
	public void update(){
		if(state == 0){
			intro.update();
		}else if(state == 1){
			menu.update();
		}else if(state == 2){
			//Moves background
			bgX+=0.5;
			bgY-=0.4;
			if(bgX >= 200) bgX = 0;
			if(bgY <= -200) bgY = 0;
			
			level.update();
		}
	}
	
	public void render(Screen screen){
		if(state == 0){
			intro.render(screen);
		}else if(state == 1){
			menu.render(screen);
		}else if(state == 2){
			for(int y = -1; y < 5; y++){
				for(int x = -1; x < 7; x++){
					screen.renderTexture(Sprite.levelbg, (int)bgX + 200*x, (int)bgY + 200*y);
				}
			}
			level.render(screen);
		}
	}
	
	public int getState(){
		return state;
	}
	public void setState(int s){
		state = s;
	}
	public void setLevel(Level l){
		level = l;
	}
	
}
