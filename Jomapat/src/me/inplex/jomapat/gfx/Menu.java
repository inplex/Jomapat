package me.inplex.jomapat.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.world.BlockType;

public class Menu {

	static int selection = 0;
	static int minSelection = 0;
	static int maxSelection = 3;
	static int screen = 0;

	public static void update() {

	}

	public static void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;


		if (screen == 0) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Serif", Font.BOLD, 96));
			g.drawString("Jomapat", Jomapat.game.getWidth() / 2 - 170, 100);
			g2d.setFont(new Font("Serif", Font.BOLD, 36));
			g2d.drawString(selection == 0 ? "> START" : "  START", Jomapat.game.getWidth() / 2 - 100, Jomapat.game.getHeight() / 2 + 50);
			g2d.drawString(selection == 1 ? "> HELP" : "  HELP", Jomapat.game.getWidth() / 2 - 100, Jomapat.game.getHeight() / 2 + 100);
			g2d.drawString(selection == 2 ? "> ABOUT" : "  ABOUT", Jomapat.game.getWidth() / 2 - 100, Jomapat.game.getHeight() / 2 + 150);
			g2d.drawString(selection == 3 ? "> EXIT" : "  EXIT", Jomapat.game.getWidth() / 2 - 100, Jomapat.game.getHeight() / 2 + 200);
			g2d.setColor(new Color(0.5f, 0.5f, 0.8f, 0.3f));
			g2d.fillRect(Jomapat.game.getWidth() / 2 - 120, Jomapat.game.getHeight() / 2, 200, 220);
			for (int i = 0; i < BlockType.values().length; i++) {
				BlockType b = BlockType.values()[i];
				g2d.drawImage(b.getSprite(0), 0, i * 64, null);
				g2d.drawImage(b.getSprite(0), Jomapat.game.getWidth() - 64, i * 64, null);
			}
		} else if (screen == 1) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Serif", Font.BOLD, 48));
			g.drawString("Move using WASD", 50, 120);
			g.drawString("Break using Leftclick", 50, 220);
			g.drawString("Build using Rightclick", 50, 320);
		} else if (screen == 2) {
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Serif", Font.BOLD, 64));
			g.drawString("'Jomapat' is a Game.", 50, 120);
			g2d.setFont(new Font("courier", Font.BOLD, 20));
			g.drawString("Programming :", 50, 250);
			g2d.setFont(new Font("courier",0, 20));
			g.drawString("Joshua   'inplex'", 50, 300);
			g.drawString("Matthias 'matthinc'", 50, 330);
			g2d.setFont(new Font("courier", Font.BOLD, 20));
			g.drawString("Graphics :", 50, 390);
			g2d.setFont(new Font("courier",0, 20));
			g.drawString("Patrick  'om22'", 50, 440);
			g.drawString("www.github.com/inplex/jomapat", 50, 500);
			g2d.setFont(new Font("arial", 0, 12));
		}
	}

	public static void onClick() {

	}

	public static void onPress(int key) {
		if (screen == 0) {
			if(key == KeyEvent.VK_S||key == KeyEvent.VK_DOWN) {
				selection = (selection+1 > maxSelection) ? maxSelection : selection+1;
			} else if(key == KeyEvent.VK_W||key == KeyEvent.VK_UP) {
				selection = (selection-1 < 0) ? maxSelection : selection-1;
			} else if(key == KeyEvent.VK_ENTER) {
				if(selection == 0) { // Start
					Jomapat.game.setInMenu(false);
				} else if(selection == 1) { // Help
					screen = 1;
				} else if(selection == 2) { // About
					screen = 2;
				} else if(selection == 3) { // Exit
					Jomapat.game.stop();
				}
			}
		} else if (screen == 1) {
			if(key == KeyEvent.VK_ESCAPE) {
				screen = 0;
				selection = 0;
			}
		} else if (screen == 2) {
			if(key == KeyEvent.VK_ESCAPE) {
				screen = 0;
				selection = 0;
			}
		}
	}

}