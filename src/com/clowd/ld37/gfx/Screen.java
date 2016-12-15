package com.clowd.ld37.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.clowd.ld37.interfaces.Renderable;

public class Screen {

	private static int width, height;
	private int[] pixels;
	private Graphics g;
	
	public boolean pause = false;
	
	public List<StringObject> stringBuffer = new ArrayList<StringObject>();
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void graphics(Graphics g){
		this.g = g;
	}
	
	public void renderRect(int xp, int yp, int w, int h, int color){
		for(int y = 0; y < h; y++){
			int yy = y + yp;
			for(int x = 0; x < w; x++){
				int xx = x + xp;
				if(xx < -w || xx >= this.width || yy < 0 || yy >= this.height) break;
				if(xx < 0) xx = 0;
				pixels[xx+yy*this.width] = color;
			}
		}
	}
	
	public void renderTexture(Sprite sprite, int xp, int yp){
		for(int y = 0; y < sprite.getHeight(); y++){
			int yy = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++){
				int xx = x + xp;
				if(xx < -sprite.getWidth() || xx >= this.width || yy < 0 || yy >= this.height) break;
				if(xx < 0) xx = 0;
				if(sprite.getPixels()[x+y*sprite.getWidth()] != 0xffff00ff){
					pixels[xx+yy*this.width] = sprite.getPixels()[x+y*sprite.getWidth()];
				}
			}
		}
	}
	
	public void render(int xp, int yp, Renderable renderable){
		for(int y = 0; y < renderable.getHeight(); y++){
			int yy = y + yp;
			for(int x = 0; x < renderable.getHeight(); x++){
				int xx = x + xp;
				if(xx < -renderable.getWidth() || xx >= this.width || yy < 0 || yy >= this.height) break;
				if(xx < 0) xx = 0;
				pixels[xx+yy*this.width] = renderable.getPixels()[x+y*width];
			}
		}
	}
	
	public void shade(int xp, int yp, int w, int h, int amount){
		for(int y = 0; y < h; y++){
			int yy = y + yp;
			for(int x = 0; x < w; x++){
				int xx = x + xp;
				if(xx < 0 || xx >= this.width || yy < 0 || yy >= this.height) break;
				int col = Light.changeBrightness(pixels[xx+yy*this.width], amount);
				pixels[xx+yy*this.width] = col;
			}
		}
	}
	
	public void addText(StringObject so){
		stringBuffer.add(so);
	}
	
	public int[] getPixels(){
		return pixels;
	}
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	
}
