package me.inplex.jomapat.extra;

import me.inplex.jomapat.Jomapat;
import me.inplex.jomapat.gfx.Gui;
import me.inplex.jomapat.player.Player;
import me.inplex.jomapat.world.BlockType;

public class CommandExecutor {

	public static void executeRaw(String cmd) {
		if (!cmd.contains(" ")) {
			execute(cmd, new String[] {});
			return;
		}
		String[] spaceSplit = cmd.split(" ");
		String command = spaceSplit[0];
		if (spaceSplit.length < 2) {
			execute(command, new String[] {});
			return;
		}
		String[] args = new String[spaceSplit.length - 1];

		for (int i = 1; i < spaceSplit.length; i++) {
			args[i - 1] = spaceSplit[i];
		}
		execute(command, args);
	}

	public static void execute(String cmd, String[] args) {
		// Implement commands here
		if (cmd.equalsIgnoreCase("help")) {
			Gui.addMessage("Available Commands: help, give, setx, sety");
		} else if (cmd.equalsIgnoreCase("give")) {
			if (args.length == 1) {
				int id;
				try {
					id = Integer.parseInt(args[0]);
				} catch (Exception ex) {
					Gui.addMessage("Invalid Arguments. Usage: give <id> [amount]");
					return;
				}
				if (id > BlockType.values().length - 1) {
					Gui.addMessage("Invalid ID. Max is " + (BlockType.values().length - 1));
					return;
				}
				Jomapat.game.getInventory().addBlock(BlockType.values()[id]);
				Gui.addMessage("Gave you 1 " + BlockType.values()[id].name());
			} else if (args.length == 2) {
				int id, amount;
				try {
					id = Integer.parseInt(args[0]);
					amount = Integer.parseInt(args[1]);
				} catch (Exception ex) {
					Gui.addMessage("Invalid Arguments. Usage: give <id> [amount]");
					return;
				}
				if (id > BlockType.values().length - 1) {
					Gui.addMessage("Invalid ID. Max is " + (BlockType.values().length - 1));
					return;
				}
				Jomapat.game.getInventory().addBlock(BlockType.values()[id], amount);
				Gui.addMessage("Gave you " + amount + " " + BlockType.values()[id].name());
			} else {
				Gui.addMessage("Invalid Arguments. Usage: give <id> [amount]");
			}
		} else if (cmd.equalsIgnoreCase("setx")) {
			if (args.length != 1) {
				Gui.addMessage("Invalid Arguments. Usage: setx <x value>");
				return;
			} else {
				int x;
				try {
					x = Integer.parseInt(args[0]);
				} catch (Exception ex) {
					Gui.addMessage("Invalid Arguments. Usage: setx <x value>");
					return;
				}
				Jomapat.game.getPlayer().setX(x);
			}
		} else if (cmd.equalsIgnoreCase("sety")) {
			if (args.length != 1) {
				Gui.addMessage("Invalid Arguments. Usage: sety <y value>");
				return;
			} else {
				int y;
				try {
					y = Integer.parseInt(args[0]);
				} catch (Exception ex) {
					Gui.addMessage("Invalid Arguments. Usage: sety <y value>");
					return;
				}
				Jomapat.game.getPlayer().setY(y);
			}
			// TODO: More Commands here
		} else if (cmd.equalsIgnoreCase("noclip")) {
			Player.noclip = !Player.noclip;
		} else {
			Gui.addMessage("Command not found. Type 'help' for a list.");
		}
	}

}