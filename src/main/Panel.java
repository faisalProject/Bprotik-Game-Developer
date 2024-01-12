package main;

import entity.*;
import tile.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
	
	//	SCREEN SETTING
	public final int originalSize = 16; // 16 x 16 tile
	public final int scale = 3;
	
	public final int tileSize = originalSize * scale; // 48 x 48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol * tileSize; // 768 pixels
	public final int screenHeight = maxScreenRow * tileSize; // 576 pixels
	
	//	WORLD SETTING
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	//	FPS
	int fps = 60;
	
	TileManager TileM = new TileManager(this);
	KeyHandler handler = new KeyHandler();
	Thread thread;
	public Player player = new Player(this,handler);	
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setDoubleBuffered(true);
		this.addKeyListener(handler);
		this.setBackground(Color.black);
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		TileM.draw(g2);

		player.draw(g2);
		
		g2.dispose();
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void update() {
		player.update();
 	}
	
	@Override
	public void run() {
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		double nextDrawTime = System.nanoTime() + drawInterval;
		while(thread != null) {
			currentTime = System.nanoTime();
			delta += ( currentTime - lastTime ) / drawInterval;
			timer += ( currentTime - lastTime );
			lastTime = currentTime;
			
			if ( delta >= 1 ) {				
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if ( timer > 1000000000 ) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
}
