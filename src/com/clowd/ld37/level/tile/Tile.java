package com.clowd.ld37.level.tile;

import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;

public class Tile {

	public static Tile floortile = new FloorTile(0xcccccc);
	public static Tile walltile = new WallTile(0xcc11aa);
	public static Tile endtile = new FloorTile(0xffffff);
	
	public static Tile voidtile = new FloorTile(0x0);
	
	protected Sprite sprite;
	protected int col;
	
	protected Tile(int col){
		this.col = col;
	}
	
	public void update(){
		
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getColor(){
		return col;
	}
	
	public void setColor(int col){
		this.col = col;
	}
	
}
