package me.inplex.jomapat.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.extra.CommandExecutor;
import me.inplex.jomapat.extra.Util;
import me.inplex.jomapat.world.BlockType;

public class Gui {
	private static List<String> messages = new ArrayList<String>();
	private static String actMsg = "";
	public static boolean showChat = true;
	public static boolean chatTyping = false;
	public static String chatMessage = "";

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
				if (acty > Jomapat.game.getHeight()-30) {
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
		if (button(g, Jomapat.game.getWidth() / 2 - width / 2 + 130, Jomapat.game.getHeight() / 2 - height / 2 - 1 + 250, 100, 20, "drop")) {
			Jomapat.game.getInventory().removeBlock(BlockType.values()[Jomapat.game.getInventory().getSelected()]);
		}

		g.setColor(Color.WHITE);
		g.drawString("health", Jomapat.game.getWidth() / 2 - width / 2 + 4, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 100 + 5);
		g.drawString("stamina", Jomapat.game.getWidth() / 2 - width / 2 + 4, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 130 + 5);
		g.setColor(new Color(0x555555));
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 4 + 100 - 1, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 100 - 1, 100 + 2, 10 + 2);
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 4 + 100 - 1, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 130 - 1, 100 + 2, 10 + 2);
		g.setColor(new Color(0x000000));
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 4 + 100, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 100, 100, 10);
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 4 + 100, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 130, 100, 10);
		g.setColor(new Color(0x00BB00));
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 4 + 100, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 130,
				Jomapat.game.getPlayer().health, 10);
		g.setColor(new Color(0xEE0000));
		g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 4 + 100, Jomapat.game.getHeight() / 2 - height / 2 + 4 + 100,
				Jomapat.game.getPlayer().stamina, 10);

		g.setColor(new Color(0xFFFFFF));

		for (int i = 0; i < BlockType.values().length; i++) {
			g.drawImage(Util.getScaledImage(BlockType.values()[i].getSprite(0), 32, 32), Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40,
					Jomapat.game.getHeight() / 2 - height / 2 + 30, null);
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 30, 15, 15);
			g.setColor(Color.WHITE);
			g.setFont(new Font("courier", 0, 12));
			g.drawString("" + Jomapat.game.getInventory().getBlockAmount(BlockType.values()[i]), Jomapat.game.getWidth() / 2 - width / 2 + 10 + i
					* 40, Jomapat.game.getHeight() / 2 - height / 2 + 30 + 10);
			if (Jomapat.game.getInventory().getSelected() == i) {
				g.setColor(Color.RED);
				g.drawRect(Jomapat.game.getWidth() / 2 - width / 2 + 10 + i * 40, Jomapat.game.getHeight() / 2 - height / 2 + 30, 32, 32);
			}
		}

		if (Jomapat.game.getInput().isMouseLeftDown()) {
			int mouseX = Jomapat.game.getInput().getMousePosX();
			int mouseY = Jomapat.game.getInput().getMousePosY();

			if (mouseX >= Jomapat.game.getWidth() / 2 - width / 2 - 1 + width - 10 && mouseX <= Jomapat.game.getWidth() / 2 - width / 2 - 1 + width
					&& mouseY <= Jomapat.game.getHeight() / 2 - height / 2 + 13 && mouseY <= Jomapat.game.getHeight() / 2 - height / 2 + 23) {
				Jomapat.game.getInventory().show(false);
			}
		}

	}

	public static void typed() {
		addMessage("> " + chatMessage);
		CommandExecutor.executeRaw(chatMessage);
		chatMessage = "";
	}

}
