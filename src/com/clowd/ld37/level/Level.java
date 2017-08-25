package com.clowd.ld37.level;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.clowd.ld37.StateManager;
import com.clowd.ld37.entity.Entity;
import com.clowd.ld37.entity.trap.Trap;
import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.gfx.StringObject;
import com.clowd.ld37.input.Keyboard;
import com.clowd.ld37.level.tile.Tile;

public class Level {
	
	//Level 
	protected String path;
	protected int width, height;
	protected int[] tiles;
	
	protected int levelNum;
	
	//Coord data
	protected int levelX, levelY;
	protected double playerX, playerY;
	protected int spawnX, spawnY;
	
	//Trap vars
	protected int range;
	protected boolean trapVision;
	
	//Item vars
	protected boolean lightBomb;
	protected int lightBombD;
	protected boolean lightBall;
	protected int lightBallD;
	protected boolean trapSensor;
	protected int trapSensorD;
	protected int itemTimer;
	
	//Spawn vars
	protected boolean start;
	protected int startTimer;
	protected int startX;
	protected int startY;
	
	//Goal vars
	protected boolean end;
	protected int endX;
	protected int endY;
	
	protected boolean nextLevel;
	
	protected boolean pause;
	
	//Timer
	protected int timeS;
	protected int timeMS;
	
	//Level color
	protected int tileCol;
	protected double timerCol;
	
	protected boolean respawn;
	//Level ratings
	protected static String[] ratings = new String[]{"A", "A", "", "A", "A", "", "", "", "", "", "A", "", "", ""};
	
	//Entity lists
	protected List<Entity> entities = new ArrayList<Entity>();
	protected List<Entity> addentities = new ArrayList<Entity>();
	protected List<Entity> removeenetities = new ArrayList<Entity>();
	
	protected StateManager manager;
	
	//Set up level from image
	public Level(String path, StateManager manager){
		this.path = path;
		this.manager = manager;
		levelNum = 0;
		levelX = 0;
		levelY = 0;
		spawnX = 0;
		spawnY = 0;
		range = 40;
		trapVision = false;
		lightBomb = false;
		lightBall = false;
		trapSensor = false;
		lightBallD = 700;
		lightBombD = 200;
		trapSensorD = 2000;
		itemTimer = 0;
		start = true;
		startTimer = 500;
		startX = 0;
		startY = 1200;
		endX = -1000;
		endY = 0;
		end = false;
		nextLevel = false;
		timeS = 0;
		timeMS = 0;
		tileCol = 0xcc11aa;
		timerCol = 0;
		respawn = false;
		loadLevel();
	}
	
	//Load level from image
	public void loadLevel(){
		try{
			BufferedImage image = ImageIO.read(Level.class.getResourceAsStream(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		}catch (IOException e){
			e.printStackTrace();
		}
		
		levelX = (1100 - width*30)/2; 
		levelY = (700 - height*30)/2; 
		
		generateLevel();
	}
	
	public void generateLevel(){
		
	}
	
	public void addEntity(Entity e){
		addentities.add(e);
	}
	
	public void removeEntity(Entity e){
		removeenetities.add(e);
	}
	
	public void updateEntities(){
		for(Entity e : entities){
			e.update();
		}
		for(Entity e : addentities){
			entities.add(e);
		}
		addentities.clear();
		for(Entity e : removeenetities){
			entities.remove(e);
		}
		removeenetities.clear();
	}
	
	public void update(){
		//Pause
		if(Keyboard.keyTyped(KeyEvent.VK_ESCAPE)) pause = !pause;
		if(!pause){
		
		//Item updates
		if(!lightBomb && !lightBall) rangeGo(40);
		if(!trapSensor) trapVision = false;
		
		if(lightBomb){
			itemTimer++;
			if(itemTimer > lightBombD){
				itemTimer = 0;
				lightBomb = false;
			}
			rangeGo(1000);
		}else if(lightBall){
			itemTimer++;
			if(itemTimer > lightBallD){
				itemTimer = 0;
				lightBall = false;
			}
			rangeGo(100);
		}else if(trapSensor){
			itemTimer++;
			if(itemTimer > trapSensorD){
				itemTimer = 0;
				trapSensor = false;
				trapVision = false;
			}
			trapVision = true;
		}
		
		//Start and end info animation
		if(startY > 0){
			startY -= 20;
		}
		if(!start){
			if(startX < 1000){
				startX+=20;
			}
			if(startTimer > 0){
				startTimer--;
				range = 1000;
			}
		}
		if(end){
			if(endX < 0){
				endX+=20;
			}
		}
		if(nextLevel){
			if(endY > -1200){
				endY-=20;
			}
		}
		
		if(!start && !end){
			timeMS++;
			if(timeMS == 100){
				timeMS = 0;
				timeS++;
			}
		}
		
		timerCol++;
		if(timerCol < 50){
			Tile.walltile.setColor((int)(tileCol + 1.05*timerCol));
		}else if(timerCol < 150){
			Tile.walltile.setColor((int)(tileCol + (100-timerCol)*1.05));
		}else if(timerCol < 200){
			Tile.walltile.setColor((int)(tileCol + (timerCol-200)*1.05));
		}else{
			timerCol = 0;
		}
		
		//Check for traps
		if(Keyboard.keyTyped(KeyEvent.VK_SPACE)){
			boolean h = false;
			for(Entity e : entities){
				if(e instanceof Trap){
					double px = Math.abs((playerX + 10)-(e.getX()+10));
					double py = Math.abs((playerY + 10)-(e.getY()+10));
					double dist = Math.sqrt((px*px)+(py*py));
					if(dist < 70){
						((Trap)e).setOn(false);
						h = true;
					}
				}
			}
			if(h == false){
				respawn = true;
			}
		}
		
		update2();
		
		}else{
			
		}
	}
	
	//Animate the changing view distance
	public void rangeGo(int target){
		if(range < target){
			range+=5;
		}else if(range > target){
			range-=5;
		}
	}
	
	public void update2(){
		
	}
	
	public void render(Screen screen){
		if(!start  && !nextLevel){
		//Tiles
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(getTile(x, y) != null){
					getTile(x, y).render((x*30)+levelX, (y*30)+levelY, screen);
				}
			}
		}
		
		//Entities
		for(Entity e : entities){
			e.render(screen);
		}
		
		//Overlay
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(getTile(x, y) != null){
					if(Math.abs((x*30)+levelX - playerX+10) < 1.5*range && Math.abs((y*30)+levelY - playerY+10) < 1.5*range ){
						for(int y2 = 0; y2 < 30; y2++){
							for(int x2 = 0; x2 < 30; x2++){
								int xp = (x*30)+levelX + x2;
								int yp = (y*30)+levelY + y2;
								int xd = Math.abs(xp-(int)(playerX+10));
								int yd = Math.abs(yp-(int)(playerY+10));
								if(Math.sqrt((xd*xd) + (yd*yd)) > range || pause){
									screen.renderRect(xp, yp, 1, 1, 0x0);
								}
							}
						}
					}else{
						screen.renderRect(Math.abs((x*30)+levelX), Math.abs((y*30)+levelY), 30, 30, 0x0);
					}
					
				}
			}
		}
		
		}
		
		//Render traps if on
		for(Entity e : entities){
			if(e instanceof Trap && ((Trap)e).getOn()){
				e.render(screen);
			}
		}
		
		//GUI
		if(!(this instanceof FinalLevel)){
		screen.addText(new StringObject(("TIME: " + timeS + "." + timeMS), 10+startY+endY, 40, 40, Color.WHITE));
		screen.addText(new StringObject(("LEVEL " + levelNum), 450+startY+endY, 40, 40, Color.WHITE));
		if(lightBomb){
			screen.renderTexture(Sprite.lightbomb, 860, 12);
			screen.renderRect(880, 10, (int)(200-200.0*((double)itemTimer/lightBombD)), 20, 0xffff00);
		}else if(lightBall){
			screen.renderTexture(Sprite.lightball, 860, 12);
			screen.renderRect(880, 10, (int)(200-200.0*((double)itemTimer/lightBallD)), 20, 0xffff00);
		}else if(trapSensor){
			screen.renderTexture(Sprite.trapsensor, 860, 12);
			screen.renderRect(880, 10, (int)(200-200.0*((double)itemTimer/trapSensorD)), 20, 0x00ffff);
		}
		}
		
		render2(screen);
		
		screen.pause = pause;
	}
	
	public void render2(Screen screen){
		
	}
	
	//Get tile type
	public Tile getTile(int x, int y){
		if(tiles[x+y*width] == 0xffff0000) return Tile.floortile;
		else if(tiles[x+y*width] == 0xff000000) return Tile.walltile;
		else if(tiles[x+y*width] == 0xff0000ff) return Tile.endtile;
		else return null;
	}
	
	public List<Entity> getEntites(){
		return entities;
	}
	public int getLevelX(){
		return levelX;
	}
	public int getLevelY(){
		return levelY;
	}
	public int getSpawnX(){
		return spawnX;
	}
	public int getSpawnY(){
		return spawnY;
	}
	public double getPlayerX(){
		return playerX;
	}
	public double getPlayerY(){
		return playerY;
	}
	public void setPlayerX(double x){
		playerX = x;
	}
	public void setPlayerY(double y){
		playerY = y;
	}
	public void setRange(int r){
		range = r;
	}
	public boolean getTrapVision(){
		return trapVision;
	}
	public void setTrapVision(boolean v){
		trapVision = v;
	}
	public void activateLightBomb(){
		lightBomb = true;
		lightBall = false;
		trapSensor = false;
		itemTimer = 0;
	}
	public void activateLightBall(){
		lightBall = true;
		lightBomb = false;
		trapSensor = false;
		itemTimer = 0;
	}
	public void activateTrapSensor(){
		trapSensor = true;
		lightBomb = false;
		lightBall = false;
		itemTimer = 0;
	}
	public void setEnd(){
		end = true;
	}
	public boolean getRespawn(){
		return respawn;
	}
	public void setRespawn(boolean r){
		respawn = r;
	}
}
