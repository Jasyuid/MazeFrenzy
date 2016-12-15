package com.clowd.ld37.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	private String path;
	private int[] pixels;
	private int width;
	
	
	public static Texture test = new Texture("/test.png");
	public static Texture player = new Texture("/entities/player.png");
	public static Texture levelbg = new Texture("/level_bg.png");
	public static Texture lightbomb = new Texture("/entities/lightbomb.png");
	public static Texture lightball = new Texture("/entities/lightball.png");
	public static Texture trapsensor = new Texture("/entities/trapsensor.png");
	public static Texture title = new Texture("/title.png");
	
	
	public Texture(String path){
		this.path = path;
		loadSheet();
	}
	
	public void loadSheet(){
		BufferedImage image = null;
		try{
			image = ImageIO.read(Texture.class.getResourceAsStream(path));
		}catch(IOException e){
			e.printStackTrace();
		}
		if(image == null) return;
		
		int w = image.getWidth();
		int h = image.getHeight();
		pixels = image.getRGB(0, 0, w, h, null, 0, w);
		
		width = w;
	}
	
	public int[] getPixels(){
		return pixels;
	}
	public int getWidth(){
		return width;
	}
	
}
