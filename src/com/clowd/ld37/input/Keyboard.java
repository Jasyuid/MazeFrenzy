package com.clowd.ld37.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Keyboard extends KeyAdapter{
	//Key array
	private static List<Integer> pressed = new ArrayList<Integer>();
	private static boolean[] keys = new boolean[65536];
	
	//Returns if key is pressed
	public static boolean keyPressed(int key){
		return keys[key];
	}
	
	//Returns if key is pressed and released
	public static boolean keyTyped(int key){
		if(!keys[key]) return false;
		if(pressed.contains(new Integer(key))) return false;
		pressed.add(new Integer(key));
		return true;
	}
	
	//Set key as pressed
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
	
	//Set key as released
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
		if(pressed.contains(new Integer(e.getKeyCode()))) pressed.remove(new Integer(e.getKeyCode()));
		
	}
	
}
