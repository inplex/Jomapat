package me.inplex.jomapat;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gui {
	private static List<String> messages = new ArrayList<String>();
	private static String actMsg="";
	
	
	
	public static void showPopupMessage(String message){
      actMsg = message;}
	
	public static void clearPopup(){
		actMsg="";}
	
	public static void addMessage(String msg){

		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		messages.add(0,time+" : "+msg);
	}
	
	static String toDelete = "";
	
	public static void renderGui(Graphics g){
		if (actMsg!=""){
			g.setColor(new Color(0x000000,0,0,100));
			g.fillRect(10, 10, 200, 30);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(actMsg,20,30);
		}
		

		
		g.setColor(new Color(0x000000,0,0,100));
		g.fillRect(10, Jomapat.game.getHeight()-200, 200, 190);
		g.setColor(new Color(0xFFFFFF));
		int acty = Jomapat.game.getHeight()-190;
		toDelete="";
		for (String msg : messages){
			acty = acty + 20;
			g.drawString(msg, 20, acty);
			if (acty-Jomapat.game.getHeight()-190 > 150){toDelete=msg;}
		}
		
		if (toDelete!=""){messages.remove(toDelete);}
		
	}
	
	

}
