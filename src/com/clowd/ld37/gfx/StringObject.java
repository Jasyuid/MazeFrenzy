package com.clowd.ld37.gfx;

import java.awt.Color;
import java.awt.Font;

public class StringObject {

	public String string;
	public int xp;
	public int yp;
	public Color color;
	public Font font;
	
	public StringObject(String string, int xp, int yp, Color color){
		this.string = string;
		this.xp = xp;
		this.yp = yp;
		this.color = color;
		font = new Font("Audiowide", Font.BOLD, 30);
	}
	public StringObject(String string, int xp, int yp, int size, Color color){
		this.string = string;
		this.xp = xp;
		this.yp = yp;
		this.color = color;
		font = new Font("Audiowide", Font.PLAIN, size);
	}
	
	public StringObject(String string, int xp, int yp, int size, int col){
		this.string = string;
		this.xp = xp;
		this.yp = yp;
		color = new Color((col & 0xff0000) >> 16, (col & 0xff00) >> 8, (col & 0xff), 255);
		font = new Font("Audiowide", Font.PLAIN, size);
	}
	
}
