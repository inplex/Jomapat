package me.inplex.jomapat.extra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.gfx.Gui;
import me.inplex.jomapat.gfx.Menu;
import me.inplex.jomapat.player.Recipe;
import me.inplex.jomapat.world.WorldSave;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private boolean[] keysDown;

	public boolean singleClick = false;

	public InputHandler() {
		keysDown = new boolean[120];
	}

	public boolean isKeyDown(int key) {
		return keysDown[key];
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keysDown[arg0.getKeyCode()] = true;
		if (Jomapat.game.isInMenu()) {
			Menu.onPress(arg0.getKeyCode());
		} else {
			if (Gui.chatTyping) {
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (Gui.chatMessage.length() > 0) {
						Gui.chatMessage = Gui.chatMessage.substring(0, Gui.chatMessage.length() - 1);
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Gui.chatTyping) {
						if (!Gui.chatMessage.equals("")) {
							Gui.typed();
							Gui.chatTyping = false;
						} else {
							Gui.chatTyping = false;
						}
					} else {
						Gui.chatTyping = true;
						Gui.chatMessage = "";
					}
				} else {
					Gui.chatMessage += arg0.getKeyChar();
				}
			} else {
				if (arg0.getKeyCode() == KeyEvent.VK_K) {
					try {
						WorldSave.save(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_L) {
					try {
						WorldSave.load(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_M) {
					Jomapat.game.minimalMode = !Jomapat.game.minimalMode;
				} else if (arg0.getKeyCode() == KeyEvent.VK_E) {
					if (Jomapat.game.getInventory().isVisible()) {
						Jomapat.game.getInventory().show(false);
					} else {
						Jomapat.game.getInventory().show(true);
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_R) {
					Recipe.shown = !Recipe.shown;
				} else if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
					Jomapat.game.getPlayer().jump();
				} else if (arg0.getKeyCode() == KeyEvent.VK_C) {
					Gui.showChat = !Gui.showChat;
				} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (Gui.chatTyping) {
						Gui.chatMessage = "";
						Gui.chatTyping = false;
					} else {
						Jomapat.game.inMenu = true;
					}
				} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if (Gui.chatTyping) {
						if (!Gui.chatMessage.equals("")) {
							Gui.typed();
							Gui.chatTyping = false;
						} else {
							Gui.chatTyping = false;
						}
					} else {
						Gui.chatTyping = true;
						Gui.chatMessage = "";
					}
				}
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

	boolean mouseDownLeft, mouseDownRight;

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
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			mouseDownLeft = true;
		} else if (arg0.getButton() == MouseEvent.BUTTON3) {
			mouseDownRight = true;
		}
		if (Jomapat.game.isInMenu()) {
			Menu.onClick();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			mouseDownLeft = false;
			singleClick = true;
		} else if (arg0.getButton() == MouseEvent.BUTTON3) {
			mouseDownRight = false;
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	int mouseX, mouseY;

	@Override
	public void mouseMoved(MouseEvent arg0) {

		mouseX = arg0.getX();
		mouseY = arg0.getY();

	}

	public int getMousePosX() {
		return mouseX;
	}

	public int getMousePosY() {
		return mouseY;
	}

	public boolean isMouseLeftDown() {
		return mouseDownLeft;
	}

	public boolean isMouseRightDown() {
		return mouseDownRight;
	}

	public boolean getMouseSingleClick() {
		if (singleClick) {
			singleClick = false;
			return true;
		}
		return false;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		
		Gui.selectedBlock = Gui.selectedBlock + notches;
		if (Gui.selectedBlock>3){Gui.selectedBlock=1;}
		if (Gui.selectedBlock<1){Gui.selectedBlock=3;}
			
		if(Recipe.shown) {
			if (notches > 0) {
				Recipe.setSelected(Recipe.getSelected() + 1);
				if (Recipe.getSelected() > Recipe.values().length - 1) {
					Recipe.setSelected(0);
				}
			} else {
				Recipe.setSelected(Recipe.getSelected() - 1);
				if (Recipe.getSelected() < 0) {
					Recipe.setSelected(Recipe.values().length - 1);
				}
			}
		}
	}

}
