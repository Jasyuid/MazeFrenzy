package com.clowd.ld37.interfaces;

import com.clowd.ld37.gfx.Screen;

public interface Renderable {
	
	public int getWidth();
	
	public int getHeight();
	
	public int[] getPixels();
	
	public void render(Screen screen);
	
}
