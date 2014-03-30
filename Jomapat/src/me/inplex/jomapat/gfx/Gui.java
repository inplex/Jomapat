package me.inplex.jomapat.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.CommandExecutor;
import me.inplex.jomapat.extra.Util;
import me.inplex.jomapat.player.ItemStack;
import me.inplex.jomapat.player.Recipe;
import me.inplex.jomapat.world.BlockType;

public class Gui {
	private static List<String> messages = new ArrayList<String>();
	private static String actMsg = "";
	public static boolean showChat = false;
	public static boolean chatTyping = false;
	public static String chatMessage = "";
	private BufferedImage invent;
	
	static BlockType pick = null;
	static BlockType slot1=null;
	static BlockType slot2=null;
	static BlockType slot3=null;

	public static void showPopupMessage(String message) {
		actMsg = message;
	}

	public static void clearPopup() {
		actMsg = "";
	}

	private static String removeBadWords(String text) {
		return text;
	}

	public static void addMessage(String msg) {

		String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		msg = removeBadWords(msg);
		messages.add(0, time + " : " + msg);
	}

	static String toDelete = "";

	public static void renderGui(Graphics g) {
		if (showChat == true) {
			if (actMsg != "") {
				g.setColor(new Color(0, 0, 0, 250));
				g.fillRect(10, 10, 200, 30);
				g.setColor(new Color(0xFFFFFF));
				g.drawString(actMsg, 20, 30);
			}
			g.setColor(new Color(0x000000, 0, 0, 50));
			g.fillRect(10, Jomapat.game.getHeight() - 220, 300, 190);
			g.setColor(new Color(0xFFFFFF));
			int acty = Jomapat.game.getHeight() - 220;
			toDelete = "";
			for (String msg : messages) {
				acty = acty + 20;
				g.drawString(msg, 20, acty);
				if (acty > Jomapat.game.getHeight() - 30) {
					toDelete = msg;
				}

			}

			if (toDelete != "") {
				messages.remove(toDelete);
			}

			/*
			 * g.setColor(new Color(100,100,100,100)); g.fillRect(10, 10, 100,
			 * 10); g.fillRect(130, 10, 100, 10); g.setColor(new
			 * Color(0x00BB00)); g.fillRect(10,10,
			 * Jomapat.game.getPlayer().stamina, 10); g.setColor(new
			 * Color(0xEE0000)); g.fillRect(130,10,
			 * Jomapat.game.getPlayer().health, 10);
			 */

			if (chatTyping) {
				g.setColor(new Color(0, 0, 0, 100));
				g.fillRect(10, Jomapat.game.getHeight() - 30, 300, 20);
				g.setColor(new Color(0xFFFFFF));
				g.drawString(chatMessage, 10, Jomapat.game.getHeight() - 10);
			}
		}
	}

	public static boolean button(Graphics g, int x, int y, int w, int h, String text) {
		int mouseX = Jomapat.game.getInput().getMousePosX();
		int mouseY = Jomapat.game.getInput().getMousePosY();
		g.setColor(new Color(0x555555));
		g.drawRect(x, y, w, h);
		if (mouseX > x && mouseY > y && mouseX < x + w && mouseY < y + h) {
			if (Jomapat.game.getInput().getMouseSingleClick()) {
				return true;
			}
			g.setColor(new Color(0xDDDDDD));
		} else {
			g.setColor(new Color(0xBBBBBB));
		}
		g.fillRect(x + 1, y + 1, w - 2, h - 2);
		g.setColor(new Color(0));
		g.drawString(text, x + 5, y + 14);
		return false;
	}

	public static void showDiggingBar(Graphics g, int x, int y, int state) {
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(x, y, 70, 3);
		g.setColor(new Color(0x00FFAA));
		g.fillRect(x, y, state, 3);
	}

	public static void showInventory(Graphics g) {
		int xStart = Jomapat.game.getWidth() / 2 -  640 /2;
		int yStart = Jomapat.game.getHeight() / 2 - 480 /2;
		int mouseX = Jomapat.game.getInput().getMousePosX();
		int mouseY = Jomapat.game.getInput().getMousePosY();
		int imageX = 20;
		int imageY = 50;

		//Draw Inventory Graphics
		g.drawImage(Jomapat.game.invent, xStart, yStart, null);
		
		for (int i=0;i<BlockType.values().length;i++){
			g.drawImage(Util.getScaledImage(BlockType.values()[i].getSprite(0),32,32),xStart+imageX,yStart+imageY,null);
			g.setColor(new Color(0xFFFFFF));
			g.setFont(new Font("arial", Font.BOLD, 12));
			g.drawString(""+Jomapat.game.getInventory().getBlockAmount(BlockType.values()[i]), xStart+imageX,yStart+imageY+10);
			
			//Item drag
			if (Jomapat.game.getInput().isMouseLeftDown()&&mouseX>xStart+imageX&&mouseY>yStart+imageY&&mouseX<xStart+imageX+32&&mouseY<yStart+imageY+32){
				pick = BlockType.values()[i];
			}
			
			imageX = imageX + 40;
			if (imageX>600){
				imageX = 20;
				imageY = imageY+40;
			}
		}
		
		
		//Move dragged block
		if (pick!=null){
			g.drawImage(Util.getScaledImage(pick.getSprite(0), 32, 32), mouseX,mouseY,null);
		}
		//Remove dragged item
		if (Jomapat.game.getInput().isMouseRightDown()){
			pick = null;
		}
		
		//Drop item in Slot
		if (Jomapat.game.getInput().isMouseLeftDown()&&pick!=null&&Util.ptInRect(mouseX, mouseY, 7+xStart, 430+yStart,45, 45)){
			slot1 = pick;
			Jomapat.game.getInventory().setSelected(slot1.ordinal());
			pick=null;
		}
		if (Jomapat.game.getInput().isMouseLeftDown()&&pick!=null&&Util.ptInRect(mouseX, mouseY, 60+xStart, 430+yStart,45, 45)){
			slot2 = pick;
			pick=null;
		}
		if (Jomapat.game.getInput().isMouseLeftDown()&&pick!=null&&Util.ptInRect(mouseX, mouseY, 120+xStart, 430+yStart,45, 45)){
			slot3 = pick;
			pick=null;
		}
		
		//Redraw Slots
		if (slot1!=null){
			g.drawImage(Util.getScaledImage(slot1.getSprite(0),32,32), 12+xStart,435+yStart,null);
		}
		if (slot2!=null){
			g.drawImage(Util.getScaledImage(slot2.getSprite(0),32,32), 65+xStart,435+yStart,null);
		}
		if (slot3!=null){
			g.drawImage(Util.getScaledImage(slot3.getSprite(0),32,32), 125+xStart,435+yStart,null);
		}

		
	}
	
	public static void showBlockSelection(Graphics g){
		int xStart = Jomapat.game.getWidth() / 2 -  100;
		int yStart = Jomapat.game.getHeight()-40;
		
		g.setColor(new Color(0,0,0,200));
		g.fillRect(xStart, yStart, 200, 40);
		
	}

	public static void showRecipes(Graphics g) {
		int width = 600, height = 300;
		g.setColor(new Color(0, 0, 0, 160));
		g.fillRect(0, 0, Jomapat.game.getWidth(), Jomapat.game.getHeight());
		g.setColor(new Color(0x999999));
		g.drawRect(Jomapat.game.getWidth() / 2 - width / 2 - 1, Jomapat.game.getHeight() / 2 - height / 2 - 1, width + 1, height + 1);
		g.setColor(new Color(0x888888));
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2, Jomapat.game.getHeight() / 2 - height / 2, width, height);
		g.setColor(new Color(0x000000));
		g.drawString("X", Jomapat.game.getWidth() / 2 - width / 2 - 1 + width - 10, Jomapat.game.getHeight() / 2 - height / 2 + 13);

		if (button(g, Jomapat.game.getWidth() / 2 - width / 2 + 20, Jomapat.game.getHeight() / 2 - height / 2 - 1 + 250, 100, 20, "close")) {
			Jomapat.game.getInventory().show(false);
		}

		if (button(g, Jomapat.game.getWidth() / 2 - width / 2 + 130, Jomapat.game.getHeight() / 2 - height / 2 - 1 + 250, 100, 20, "craft")) {
			Recipe.craft(Recipe.values()[Recipe.selected]);
		}

		for (int i = 0; i < Recipe.values().length; i++) {
			Recipe r = Recipe.values()[i];

			g.drawImage(Util.getScaledImage(r.getOutput().getBlock().getSprite(0), 32, 32), Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40,
					Jomapat.game.getHeight() / 2 - height / 2 + 30, null);
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 30, 15, 15);
			g.setColor(Color.WHITE);
			g.setFont(new Font("courier", 0, 12));
			g.drawString("" + r.getOutput().getAmount(), Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height
					/ 2 + 30 + 10);
			if (!Recipe.canCraft(r)) {
				g.setColor(new Color(0, 0, 0, 0x55));
				g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 30, 32, 32);
			}
			if (Recipe.getSelected() == i) {
				g.setColor(Color.RED);
				g.drawRect(Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 30, 32, 32);
			}

			g.setColor(Color.WHITE);
			g.drawString("=", Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40 + 12, Jomapat.game.getHeight() / 2 - height / 2 + 30 + 48);
			int yOffset = 60;
			for (int j = 0; j < r.getIngredients().length; j++) {
				ItemStack is = r.getIngredients()[j];
				g.drawImage(Util.getScaledImage(is.getBlock().getSprite(0), 32, 32), Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40,
						Jomapat.game.getHeight() / 2 - height / 2 + 30 + yOffset, null);
				g.setColor(new Color(0, 0, 0, 100));
				g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 30 + yOffset, 15, 15);
				g.setColor(Color.WHITE);
				g.setFont(new Font("courier", 0, 12));
				boolean hasEnough = true;
				if(Jomapat.game.getInventory().getBlockAmount(is.getBlock()) < is.getAmount()) {
					hasEnough = false;
				}
				g.setColor(hasEnough ? Color.GREEN : Color.RED);
				g.drawString("" + is.getAmount(), Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 40 + yOffset);
				
				yOffset += 40;
			}
		}

	}

	public static void typed() {
		addMessage("> " + chatMessage);
		CommandExecutor.executeRaw(chatMessage);
		chatMessage = "";
	}

}
