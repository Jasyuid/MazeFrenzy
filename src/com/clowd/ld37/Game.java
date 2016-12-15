package com.clowd.ld37;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.clowd.ld37.gfx.Screen;
import com.clowd.ld37.gfx.Sprite;
import com.clowd.ld37.gfx.StringObject;
import com.clowd.ld37.input.Keyboard;

public class Game extends Canvas implements Runnable{

	private JFrame frame;
	private final String TITLE = "Maze Frenzy";
	private final int WIDTH = 1100;
	private final int HEIGHT = 700;
	private final int SCALE = 1;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private StateManager manager;
	
	public Game(){
		Dimension size = new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
		setPreferredSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		
		addKeyListener(new Keyboard());
		
		manager = new StateManager(this);
		
		GraphicsEnvironment ge = null;
	    try{
	      ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/fonts/Audiowide-Regular.ttf"))); //Audiowide
	    } catch(FontFormatException e){} catch (IOException e){}  
	    
	    System.setProperty("prism.lcdtext", "false");
	    
	}
	
	public void start(){
		if(!running){
			running = true;
			thread = new Thread(this, "Game Thread");
			thread.start();
		}
	}
	
	public void stop(){
		if(running){
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double ns = 1000000000.0 / 100.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		requestFocus();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1){
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
		
			while(System.currentTimeMillis() - lastTimer > 1000){
				lastTimer += 1000;
				System.out.println("UPS: " + updates + ", FPS: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void update(){
		manager.update();
		
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		screen.graphics(g);
		screen.clear();
		
		Graphics2D graphics2d = (Graphics2D) g;
		
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		manager.render(screen);
		
		for(int i = 0; i < WIDTH*HEIGHT; i++){
			pixels[i] = screen.getPixels()[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		
		for(StringObject so : screen.stringBuffer){
			String s = so.string;
			g.setColor(so.color);
			g.setFont(so.font);
			g.drawString(s, so.xp, so.yp);
		}
		screen.stringBuffer.clear();
		
		if(screen.pause && manager.getState() == 2){
			g.setColor(new Color((0xcc00aa & 0xff0000) >> 16, (0xcc00aa & 0xff00) >> 8, (0xcc00aa & 0xff), 255));
			g.fillRect(0, 290, screen.getWidth(), 170);
			g.setColor(new Color((0x222222 & 0xff0000) >> 16, (0x222222 & 0xff00) >> 8, (0x222222 & 0xff), 255));
			g.fillRect(0, 300, screen.getWidth(), 150);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Audiowide", Font.PLAIN, 90));
			g.drawString("PAUSED", 330, 380);
			g.setFont(new Font("Audiowide", Font.PLAIN, 30));
			g.drawString("Press Escape to Resume", 340, 420);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		
		game.frame = new JFrame();
		game.frame.setResizable(false);
		game.frame.setTitle(game.TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		
		URL url = Game.class.getResource("/icon.png");
		ImageIcon imgicon = new ImageIcon(url);
		game.frame.setIconImage(imgicon.getImage());
		
		game.start();
	}
	
}
