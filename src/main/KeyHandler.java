package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
//	Boolean up, down, left, right;
	
	public Boolean up = false;
	public Boolean down = false;
	public Boolean left = false;
	public Boolean right = false;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			up = true;
		}
		
		if(code == KeyEvent.VK_A) {
			left = true;
		}
		
		if(code == KeyEvent.VK_D) {
			right = true;
		}
		
		if(code == KeyEvent.VK_S) {
			down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			up = false;
		}
		
		if(code == KeyEvent.VK_A) {
			left = false;
		}
		
		if(code == KeyEvent.VK_D) {
			right = false;
		}
		
		if(code == KeyEvent.VK_S) {
			down = false;
		}
		
	}

}
