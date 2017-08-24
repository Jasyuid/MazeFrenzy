package com.clowd.ld37.gfx;

public class Sprite {

	private int x, y;
	private int width, height;
	private Texture texture;
	private int[] pixels;
	
	//All loaded sprites
	public static Sprite test = new Sprite(Texture.test, 0, 0, 50, 50);
	
	public static Sprite title = new Sprite(Texture.title, 0, 0, 930, 306);
	
	public static Sprite levelbg = new Sprite(Texture.levelbg, 0, 0, 200, 200);
	
	public static Sprite floortile = new Sprite(30, 30, 0x0);
	public static Sprite walltile = new Sprite(30, 30, 0xffffff);
	
	public static Sprite player = new Sprite(Texture.player, 0, 0, 20, 20);
	public static Sprite walkera = new Sprite(24, 24, 0xdd0000);
	public static Sprite walkerb = new Sprite(24, 24, 0x00dd00);
	public static Sprite walkerc = new Sprite(24, 24, 0xaa00dd);
	
	public static Sprite lightball = new Sprite(Texture.lightball, 0, 0, 16, 16);
	public static Sprite lightbomb = new Sprite(Texture.lightbomb, 0, 0, 16, 16);
	public static Sprite trapsensor = new Sprite(Texture.trapsensor, 0, 0, 16, 16);
	
	public static Sprite trapon = new Sprite(30, 30, 0x00dddd);
	public static Sprite trapoff = new Sprite(30, 30, 0x006666);
	
	
	public Sprite(Texture texture, int x, int y, int w, int h){
		width = w;
		height = h;
		pixels = new int[width * height];
		this.x = x;
		this.y = y;
		this.texture = texture;
		create(width, height);
	}
	
	public Sprite(int w, int h, int col){
		width = w;
		height = h;
		pixels = new int[w*h];
		create(col);
	}
	
	public void create(int w, int h){
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				pixels[x+y*w] = texture.getPixels()[(x+this.x) + (y+this.y)*texture.getWidth()];
			}
		}
	}
	
	public void create(int col){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = col;
		}
	}
	
	public int[] getPixels(){
		return pixels;
	}
	public void setPixels(int i, int p){
		pixels[i] = p;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
}
