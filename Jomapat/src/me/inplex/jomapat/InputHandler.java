package me.inplex.jomapat;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class InputHandler implements KeyListener, MouseListener {
	
	private boolean[] keysDown;
	
	public InputHandler() {
		keysDown = new boolean[120];
	}
	
	public boolean isKeyDown(int key) {
		return keysDown[key];
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		keysDown[arg0.getKeyCode()] = true;
		if(arg0.getKeyCode() == KeyEvent.VK_S) {
			try {
				WorldSave.save(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(arg0.getKeyCode() == KeyEvent.VK_L) {
			try {
				WorldSave.load(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keysDown[arg0.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
