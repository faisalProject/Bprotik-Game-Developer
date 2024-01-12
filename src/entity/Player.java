package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
	Panel gp;
	KeyHandler handler;
	
	public final int screenX, screenY;
	
	public Player(Panel gp, KeyHandler handler) {
		this.gp = gp;
		this.handler = handler;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		setDefaultValues();
		getImagePlayer();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void update() {
		if ( handler.up == true || 
			 handler.down == true || 
			 handler.left == true || 
			 handler.right == true 
		) {
			if ( handler.up == true ) {
				direction = "up1";
				worldY -= speed;
			}
			
			else if ( handler.down == true ) {
				direction = "down1";
				worldY += speed;
			} 
			
			else if ( handler.left == true ) {
				direction = "left1";
				worldX -= speed;
			}
			
			else if ( handler.right == true ) {
				direction = "right1";
				worldX += speed;
			}
			
			spriteCounter++;
			
			if ( spriteCounter > 12 ) {
				if ( spriteNum == 1 ) {
					spriteNum = 2;
				} else if ( spriteNum == 2 ) {
					spriteNum = 1;
				}
				
				spriteCounter = 0;
	 		}
		}
		
	}
	
	public void getImagePlayer() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_attack_right_2.png"));
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public void draw( Graphics2D g2 ) {
		BufferedImage image = right1;
		
		switch ( direction ) {
			case "up1":
				if ( spriteNum == 1 ) {
					image = up1;
				} 
				
				if ( spriteNum == 2 ) {
					image = up2;
				}
				
				break;
			case "down1":
				if ( spriteNum == 1 ) {					
					image = down1;
				}
				
				if ( spriteNum == 2 ) {
					image = down2;
				}
				
				break;
			case "left1":
				if ( spriteNum == 1 ) {					
					image = left1;
				}
				
				if ( spriteNum == 2 ) {
					image = left2;
				}
				break;
			case "right1":
				if ( spriteNum == 1 ) {					
					image = right1;
				}
				
				if ( spriteNum == 2 ) {
					image = right2;
				}
				break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
