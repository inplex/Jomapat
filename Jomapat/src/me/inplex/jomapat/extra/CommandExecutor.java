package me.inplex.jomapat.extra;

import me.inplex.jomapat.Jomapat;
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
		if (cmd.equalsIgnoreCase("give")) {
			if (args.length == 1) {
				int id = Integer.parseInt(args[0]);
				Jomapat.game.getInventory().addBlock(BlockType.values()[id]);
			}
		}
	}

}