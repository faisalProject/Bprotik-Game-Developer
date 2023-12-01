package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
	
	final int originalSize = 16;
	final int scale = 3;
	final int tileSize = originalSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidht = maxScreenCol * tileSize;
	final int screenHeight = maxScreenRow * tileSize;
	
	Thread thread;
	
	KeyHandler handler = new KeyHandler();
	
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	int fps = 60;
	
	public Panel() {
		this.setPreferredSize(new Dimension(screenWidht, screenHeight));
		this.setDoubleBuffered(true);
		this.addKeyListener(handler);
		this.setBackground(Color.black);
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}
	
	public void update() {
		if( handler.up ) {
			playerY -= playerSpeed;
		}
		
		if( handler.down ) {
			playerY += playerSpeed;
		}
		
		if( handler.left ) {
			playerX -= playerSpeed;
		}
		
		if( handler.right ) {
			playerX += playerSpeed;
		}
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
