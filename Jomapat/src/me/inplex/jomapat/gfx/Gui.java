package me.inplex.jomapat.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.InputHandler;

public class Gui {
	private static List<String> messages = new ArrayList<String>();
	private static String actMsg = "";

	public static void showPopupMessage(String message) {
		actMsg = message;
	}

	public static void clearPopup() {
		actMsg = "";
	}

	private static String replace(String text, String find) {
		String outParam = "";
		for (int i = 0; i < find.length(); i++) {
			outParam += "*";
		}
		return text.replaceAll("(?i)" + find, outParam);
	}

	private static String removeBadWords(String text) {
		text = replace(text, "Arsch");
		text = replace(text, "Idiot");
		return text;
	}

	public static void addMessage(String msg) {

		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		msg = removeBadWords(msg);
		messages.add(0, time + " : " + msg);
	}

	static String toDelete = "";

	public static void renderGui(Graphics g) {
		if (actMsg != "") {
			g.setColor(new Color(0x000000, 0, 0,250));
			g.fillRect(10, 10, 200, 30);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(actMsg, 20, 30);
		}

		g.setColor(new Color(0x000000, 0, 0, 200));
		g.fillRect(10, Jomapat.game.getHeight() - 200, 300, 190);
		g.setColor(new Color(0xFFFFFF));
		int acty = Jomapat.game.getHeight() - 190;
		toDelete = "";
		for (String msg : messages) {
			acty = acty + 20;
			g.drawString(msg, 20, acty);
			if (acty - Jomapat.game.getHeight() - 190 > 150) {
				toDelete = msg;
			}
		}

		if (toDelete != "") {
			messages.remove(toDelete);
		}

	}
	
	public static void showDiggingBar(Graphics g,int x,int y,int state) {
		g.setColor(new Color(0,0,0,100));
		g.fillRect(x, y, 70,3);
		g.setColor(new Color(0x00FFAA));
		g.fillRect(x, y, state, 3);
	}
	
	
	public static void showInventory(Graphics g){
		int width = 600,height=300;
		g.setColor(new Color(100,100,100,160));
		g.fillRect(0, 0, Jomapat.game.getWidth(), Jomapat.game.getHeight());
		g.setColor(new Color(0xEEEEEE));
		g.drawRect(Jomapat.game.getWidth()/2-width/2-1, Jomapat.game.getHeight()/2-height/2-1, width+1, height+1);
		g.setColor(new Color(0xBBBBBB));
		g.fillRect(Jomapat.game.getWidth()/2-width/2, Jomapat.game.getHeight()/2-height/2, width, height);
        g.setColor(new Color(0x000000));
        g.drawString("X",Jomapat.game.getWidth()/2-width/2-1+width-10,Jomapat.game.getHeight()/2-height/2+13);
			
		if (Jomapat.game.getInput().isMouseDown()) {
			int mouseX = Jomapat.game.getInput().getMousePosX();
			int mouseY = Jomapat.game.getInput().getMousePosY();
			
			if (mouseX>=Jomapat.game.getWidth()/2-width/2-1+width-10&&mouseX<=Jomapat.game.getWidth()/2-width/2-1+width&&mouseY<=Jomapat.game.getHeight()/2-height/2+13&&mouseY<=Jomapat.game.getHeight()/2-height/2+23){
				
			}
		}
		
	}
	

}
