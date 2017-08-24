package com.clowd.ld37.gfx;

public class Light {
	
	//Shifts red green and blue color channels proportionally up or down
	public static int changeBrightness(int col, int a){
		int r = (col & 0xff0000) >> 16;
		int g = (col & 0xff00) >> 8;
		int b = (col & 0xff);
		
		if((double)r/255 >= (double)g/255 && (double)r/255 >= (double)b/255){
			r += a;
			g += a*Math.abs((double)g/(double)r);
			b += a*Math.abs((double)b/(double)r);
		}else if((double)g/255 >= (double)r/255 && (double)g/255 >= (double)b/255){
			g += a;
			r += a*Math.abs((double)r/(double)g);
			b += a*Math.abs((double)b/(double)g);
		}else if((double)b/255 >= (double)r/255 && (double)b/255 >= (double)g/255){
			b += a;
			g += a*Math.abs((double)g/(double)b);
			r += a*Math.abs((double)r/(double)b);
		}
		
		if(r < 0) r = 0;
		if(g < 0) g = 0;
		if(b < 0) b = 0;
		if(r > 255) r = 255;
		if(g > 255) g = 255;
		if(b > 255) b = 255;
		
		return r << 16 | g << 8 | b;
	}
}
