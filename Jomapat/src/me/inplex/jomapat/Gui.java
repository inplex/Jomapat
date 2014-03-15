package me.inplex.jomapat;

import java.awt.Color;
import java.awt.Graphics;

public class Gui {
	private String[] chat = new String[10];
	private static String actMsg="Message";
	
	
	
	public static void showPopupMessage(String message){
      actMsg = message;
	}
	
	public static void renderGui(Graphics g){
		if (actMsg!=""){
			g.setColor(new Color(0x000000,0,0,100));
			g.fillRect(10, 10, 200, 30);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(actMsg,20,30);
		}
	}
	
	

}
