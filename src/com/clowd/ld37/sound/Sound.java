package com.clowd.ld37.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	//Loaded sounds
	public static final Sound music = new Sound("/sounds/song.wav");
	public static final Sound enter = new Sound("/sounds/enter.wav");
	public static final Sound hit = new Sound("/sounds/hit.wav");
	public static final Sound powerup = new Sound("/sounds/powerup.wav");
	public static final Sound finish = new Sound("/sounds/finish.wav");
	
	private AudioClip sound;
	
	private Sound(String name){
		//Loads audioclip
		sound = Applet.newAudioClip(Sound.class.getResource(name));
	}
	
	public void play(boolean loop){
		if(loop)
			sound.loop();
		else
			sound.play();
	}
	
	public void stop(){
		sound.stop();
	}
	
	public static void stopAll(){
		
	}
	
}
