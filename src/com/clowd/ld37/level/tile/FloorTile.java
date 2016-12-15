package com.clowd.ld37.level.tile;

import com.clowd.ld37.gfx.Screen;

public class FloorTile extends Tile{

	public FloorTile(int col){
		super(col);
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderRect(x, y, 30, 30, col);
	}
	
}
